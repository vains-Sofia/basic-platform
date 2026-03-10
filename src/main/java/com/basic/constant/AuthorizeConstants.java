package com.basic.constant;

import java.util.Set;

/**
 * 鉴权常量
 *
 * @author vains
 */
public class AuthorizeConstants {

    public static final String BASIC_PREFIX = "basic-platform:";

    /**
     * 认证信息前缀
     */
    public static final String AUTHENTICATION_PREFIX = BASIC_PREFIX + "authentication:";

    /**
     * 黑名单列表前缀
     */
    public static final String BLACKLIST_PREFIX = BASIC_PREFIX + "blacklist:";

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
