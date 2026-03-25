package com.basic.converter;

import com.basic.constant.AuthorizeConstants;
import com.basic.domain.model.BasicOAuth2AuthenticatedPrincipal;
import com.basic.domain.model.BasicUserDetails;
import com.basic.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrors;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 基于 Redis 用户信息解析Jwt的Converter
 *
 * @author vains
 */
@Component
@RequiredArgsConstructor
public class BasicJwtRedisAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final RedisTemplate<String, String> stringRedisTemplate;

    private final RedisTemplate<String, BasicUserDetails> userRedisTemplate;

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        if (source == null) {
            return null;
        }

        // 获取 用户ID
        String userId = stringRedisTemplate.opsForValue().get(AuthorizeConstants.JTI_USER_HASH + source.getId());
        // 从Redis 中获取用户信息
        BasicUserDetails userDetails = userRedisTemplate.opsForValue().get(AuthorizeConstants.USERINFO_PREFIX + userId);
        if (userDetails == null) {
            // Jwt被正常解析但是无法获取到Redis的用户信息，这种情况一般是登出、管理平台下线后出现的问题
            // RFC6750规定字符只能是 %x21 / %x23-5B/ %x5D-7E，以%x20分割(https://datatracker.ietf.org/doc/rfc6750/)
            // %x21 表示 !   %x23-5B 表示 # 到 [, 包括：# $ % & ' ( ) * + , - . / 0-9 : ; < = > ? @ A-Z 和 [
            // %x5D-7E 表示 ] 到 ~，包括：] ^ _ ` a-z { | } 和 ~    %x20 表示空格，用于分隔多个 scope 值。
            BearerTokenError bearerTokenError = BearerTokenErrors.invalidToken("Access token is invalid or has been logged out.");
            throw new OAuth2AuthenticationException(bearerTokenError);
        }

        // 返回 BearerTokenAuthentication 的实例
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, source.getTokenValue(),
                source.getIssuedAt(), source.getExpiresAt());

        // 构建资源服务用户信息
        BasicOAuth2AuthenticatedPrincipal principal = new BasicOAuth2AuthenticatedPrincipal();
        BeanUtils.copyProperties(userDetails, principal);

        // 生成属性
        Map<String, Object> attributes = JsonUtils.objectToObject(userDetails, Map.class, String.class, Object.class);
        principal.setAttributes(attributes);

        return new BearerTokenAuthentication(principal, accessToken, userDetails.getAuthorities());
    }

}
