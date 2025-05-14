package com.basic.constant;

import java.util.Set;

/**
 * 鉴权常量
 *
 * @author vains
 */
public class AuthorizeConstants {

    /**
     * access_token过期时间，单位：秒(s)
     */
    public static final long ACCESS_TOKEN_EXPIRY = 7200L;

    /**
     * refresh_token过期时间，单位：天(d)
     */
    public static final long REFRESH_TOKEN_EXPIRY = 30L;

    private static final String BASIC_PREFIX = "basic-platform:";

    /**
     * 认证信息前缀
     */
    public static final String AUTHENTICATION_PREFIX = BASIC_PREFIX + "authentication:";

    /**
     * 默认忽略鉴权的地址
     */
    public static final Set<String> DEFAULT_IGNORE_PATHS = Set.of(
            "/login",
            "/error",
            "/token/**",
            "/image/**",
            "/assets/**",
            "/favicon.ico",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

}
