package com.basic.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  token 配置
 *
 * @author vains
 */
@Data
@Component
@ConfigurationProperties(prefix = "basic.cloud.security.token")
public class TokenProperties {

    /**
     * access token 过期时间（秒）
     */
    private long accessTokenExpire;

    /**
     * refresh token 过期时间（天）
     */
    private long refreshTokenExpire;

    /**
     * Refresh Token 绝对过期时间（天）
     * 无论怎么刷新，到这个时间必须重新登录
     */
    private Long refreshTokenAbsoluteExpire = 30L;

    /**
     * jwt issuer
     */
    private String issuer;

}