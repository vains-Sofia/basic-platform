package com.basic.service.impl;

import com.basic.constant.AuthorizeConstants;
import com.basic.domain.model.BasicUserDetails;
import com.basic.domain.response.TokenResponse;
import com.basic.property.TokenProperties;
import com.basic.service.TokenService;
import com.basic.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Override
    public TokenResponse generateToken(Authentication authentication) {

        String accessToken = generateAccessToken(authentication);

        String refreshToken = UUID.randomUUID().toString();

        String refreshKey = AuthorizeConstants.AUTHENTICATION_PREFIX + refreshToken;

        if (authentication.getPrincipal() instanceof BasicUserDetails userDetails) {
            // 将用户信息存储到Redis中，方便刷新时获取
            userRedisTemplate.opsForValue().set(
                    refreshKey,
                    userDetails,
                    tokenProperties.getRefreshTokenExpire(),
                    TimeUnit.DAYS
            );
        } else {
            throw new UnsupportedOperationException("Unsupported principal type: " + authentication.getPrincipal());
        }

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

        BasicUserDetails userDetails = userRedisTemplate.opsForValue().get(key);

        if (userDetails == null) {
            throw new RuntimeException("refresh token invalid");
        }

        // refresh token rotation
        userRedisTemplate.delete(key);

        Authentication authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                        userDetails,
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