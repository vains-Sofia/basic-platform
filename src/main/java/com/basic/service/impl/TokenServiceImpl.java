package com.basic.service.impl;

import com.basic.constant.AuthorizeConstants;
import com.basic.domain.model.BasicUserDetails;
import com.basic.domain.model.RefreshTokenInfo;
import com.basic.domain.response.TokenResponse;
import com.basic.property.TokenProperties;
import com.basic.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    private final TokenProperties tokenProperties;

    private final RedisTemplate<String, Long> blackRedisTemplate;

    private final RedisTemplate<String, RefreshTokenInfo> redisTemplate;

    @Override
    public TokenResponse generateToken(Authentication authentication) {

        String accessToken = generateAccessToken(authentication);

        String refreshToken = UUID.randomUUID().toString();

        RefreshTokenInfo tokenInfo = new RefreshTokenInfo();

        tokenInfo.setUsername(authentication.getName());

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        tokenInfo.setScope(scope);

        tokenInfo.setExpireAt(Instant.now().plusSeconds(tokenProperties.getAccessTokenExpire()));

        String refreshKey = AuthorizeConstants.AUTHENTICATION_PREFIX + refreshToken;

        redisTemplate.opsForValue().set(
                refreshKey,
                tokenInfo,
                tokenProperties.getRefreshTokenExpire(),
                TimeUnit.DAYS
        );

        TokenResponse response = new TokenResponse();

        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setExpiresIn(tokenProperties.getAccessTokenExpire());

        return response;
    }

    private String generateAccessToken(Authentication authentication) {

        String userId;
        String subject;
        if (authentication.getPrincipal() instanceof BasicUserDetails userDetails) {
            subject =  userDetails.getNickname();
            userId = String.valueOf(userDetails.getId());
        } else {
            userId = "";
            subject = "";
        }

        Instant now = Instant.now();

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(tokenProperties.getIssuer())
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(tokenProperties.getAccessTokenExpire()))
                .id(UUID.randomUUID().toString())
                .claim(OAuth2ParameterNames.SCOPE, scope)
                .claim(AuthorizeConstants.CLAIM_USER_ID, userId)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {

        String key = AuthorizeConstants.AUTHENTICATION_PREFIX + refreshToken;

        RefreshTokenInfo tokenInfo = redisTemplate.opsForValue().get(key);

        if (tokenInfo == null) {
            throw new RuntimeException("refresh token invalid");
        }

        // refresh token rotation
        redisTemplate.delete(key);

        Authentication authentication =
                org.springframework.security.authentication.UsernamePasswordAuthenticationToken.authenticated(
                        tokenInfo.getUsername(),
                        null,
                        org.springframework.security.core.authority.AuthorityUtils
                                .commaSeparatedStringToAuthorityList(tokenInfo.getScope())
                );

        return generateToken(authentication);
    }

    @Override
    public void revokeToken(String accessToken) {

        Jwt jwt = jwtDecoder.decode(accessToken);

        String jti = jwt.getId();

        if (jwt.getExpiresAt() == null || jwt.getExpiresAt().isBefore(Instant.now())) {
            return;
        }
        long expire = jwt.getExpiresAt().getEpochSecond() - Instant.now().getEpochSecond();

        blackRedisTemplate.opsForValue().set(
                AuthorizeConstants.BLACKLIST_PREFIX + jti,
                1L,
                expire,
                TimeUnit.SECONDS
        );
    }
}