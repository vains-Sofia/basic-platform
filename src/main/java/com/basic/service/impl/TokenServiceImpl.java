package com.basic.service.impl;

import com.basic.constant.AuthorizeConstants;
import com.basic.domain.response.TokenResponse;
import com.basic.service.TokenService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * token service实现
 *
 * @author vains
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;

    @Resource
    private RedisTemplate<String, Authentication> redisTemplate;

    @Override
    public String generateAccessToken(Authentication authentication, long expiry) {
        // 构建JWT Token
        Instant now = Instant.now();

        // 从认证信息中获取用户权限信息
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        // 构建JWT Token的Claims
        JwtClaimsSet.Builder builder = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope);

        JwtClaimsSet claims = builder.build();

        // 生成JWT Token并返回
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public TokenResponse generateTokenResponse(Authentication authentication,
                                               long accessTokenExpiry,
                                               long refreshTokenExpiry) {

        // 生成JWT Token并返回
        String tokenValue = this.generateAccessToken(authentication, AuthorizeConstants.ACCESS_TOKEN_EXPIRY);
        String refreshToken = UUID.randomUUID().toString();
        // 存入缓存中，30天后过期
        String authorizationKey = AuthorizeConstants.AUTHENTICATION_PREFIX + refreshToken;
        redisTemplate.opsForValue().set(authorizationKey, authentication, refreshTokenExpiry, TimeUnit.DAYS);

        // 构建响应bean
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(tokenValue);
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setExpires(LocalDateTime.now().plusSeconds(accessTokenExpiry).toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        return tokenResponse;
    }
}
