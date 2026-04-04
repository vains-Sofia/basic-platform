package com.basic.service.impl;

import com.basic.constant.AuthorizeConstants;
import com.basic.domain.model.BasicUserDetails;
import com.basic.domain.model.RefreshTokenInfo;
import com.basic.domain.response.TokenResponse;
import com.basic.property.TokenProperties;
import com.basic.service.TokenService;
import com.basic.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;

    private final TokenProperties tokenProperties;

    private final RedisTemplate<String, String> stringRedisTemplate;

    private final RedisTemplate<String, BasicUserDetails> userRedisTemplate;

    private final RedisTemplate<String, RefreshTokenInfo> refreshRedisTemplate;

    @Override
    public TokenResponse generateToken(Authentication authentication) {

        String refreshToken = UUID.randomUUID().toString();

        String refreshKey = AuthorizeConstants.AUTHENTICATION_PREFIX + refreshToken;

        long absoluteExpireTime;
        BasicUserDetails basicUserDetails;

        if (authentication.getPrincipal() instanceof BasicUserDetails userDetails) {
            // 计算 绝对过期时间戳
            absoluteExpireTime = System.currentTimeMillis()
                    + TimeUnit.DAYS.toMillis(tokenProperties.getRefreshTokenAbsoluteExpire());

            basicUserDetails = userDetails;

        } else if (authentication.getPrincipal() instanceof RefreshTokenInfo refreshTokenInfo) {
            basicUserDetails = refreshTokenInfo.getUserDetails();
            absoluteExpireTime = refreshTokenInfo.getAbsoluteExpireTime();

        } else {
            throw new UnsupportedOperationException("Unsupported principal type: " + authentication.getPrincipal());
        }

        // 存储对象，过期时间 = 单次有效期
        userRedisTemplate.opsForValue().set(
                refreshKey,
                basicUserDetails,
                tokenProperties.getRefreshTokenExpire(),
                TimeUnit.DAYS
        );

        // 绝对过期时间
        stringRedisTemplate.opsForValue().set(
                refreshKey + ":expire",
                Long.toString(absoluteExpireTime),
                tokenProperties.getRefreshTokenExpire(),
                TimeUnit.DAYS
        );

        if (authentication.getPrincipal() instanceof RefreshTokenInfo refreshTokenInfo) {
            // 生成新 token
            authentication = UsernamePasswordAuthenticationToken.authenticated(
                    refreshTokenInfo.getUserDetails(),
                    null,
                    authentication.getAuthorities()
            );
        }

        String accessToken = generateAccessToken(authentication);

        TokenResponse response = new TokenResponse();

        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setExpiresIn(tokenProperties.getAccessTokenExpire());

        return response;
    }

    private String generateAccessToken(Authentication authentication) {

        String jti = UUID.randomUUID().toString();

        String userId;
        String subject;
        if (authentication.getPrincipal() instanceof BasicUserDetails userDetails) {
            subject = userDetails.getNickname();
            userId = String.valueOf(userDetails.getId());

            // 缓存 jti 和 用户ID 的映射
            stringRedisTemplate.opsForValue().set(AuthorizeConstants.JTI_USER_HASH + jti, userId, tokenProperties.getAccessTokenExpire(), TimeUnit.SECONDS);

            // 将用户信息存储到Redis中，方便自省时获取
            userRedisTemplate.opsForValue().set((AuthorizeConstants.USERINFO_PREFIX + userId), userDetails, tokenProperties.getAccessTokenExpire(), TimeUnit.SECONDS);
        } else {
            throw new UnsupportedOperationException("Unsupported principal type: " + authentication.getPrincipal());
        }

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(tokenProperties.getIssuer())
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(tokenProperties.getAccessTokenExpire()))
                .id(jti)
                .claim(AuthorizeConstants.CLAIM_USER_ID, userId)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {

        String key = AuthorizeConstants.AUTHENTICATION_PREFIX + refreshToken;

        // 1. 获取缓存信息
        BasicUserDetails userDetails = userRedisTemplate.opsForValue().get(key);
        String absoluteExpireTimeStr = stringRedisTemplate.opsForValue().get(key + ":expire");

        if (userDetails == null || absoluteExpireTimeStr == null) {
            throw new AuthenticationServiceException("refresh token invalid");
        }

        long absoluteExpireTime = Long.parseLong(absoluteExpireTimeStr);

        // 2. 校验绝对过期时间
        if (System.currentTimeMillis() > absoluteExpireTime) {
            // 清理
            refreshRedisTemplate.delete(key);
            throw new AuthenticationServiceException("refresh token 已过期，请重新登录");
        }

        // 3. 旧 token 立即作废（轮换机制）
        userRedisTemplate.delete(key);
        userRedisTemplate.delete(key + ":expire");

        RefreshTokenInfo refreshTokenInfo = new RefreshTokenInfo(userDetails, absoluteExpireTime);

        // 4. 生成新 token（新 token 会继承剩余的绝对有效期）
        Authentication authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                        refreshTokenInfo,
                        null,
                        userDetails.getAuthorities()
                );

        return generateToken(authentication);
    }

    @Override
    public void revokeToken() {
        // 当前登录用户
        BasicUserDetails loginUser = SecurityUtils.getLoginUser();
        if (loginUser == null) {
            throw new InvalidBearerTokenException("Invalid token");
        }

        // 清除 token 对应的缓存
        userRedisTemplate.delete(AuthorizeConstants.USERINFO_PREFIX + loginUser.getId());

    }
}