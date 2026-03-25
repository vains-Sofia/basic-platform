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
     * 用户主键 ID
     */
    public static final String CLAIM_USER_ID = "user_id";

    /**
     * 认证信息前缀
     */
    public static final String AUTHENTICATION_PREFIX = BASIC_PREFIX + "authentication:";

    /**
     * 用户id 与令牌唯一id的映射关系缓存key
     */
    public static final String JTI_USER_HASH = BASIC_PREFIX + "userinfo:jti:";

    /**
     * 用户信息缓存前缀
     */
    public static final String USERINFO_PREFIX = BASIC_PREFIX + "userinfo:id:";

    /**
     * 黑名单列表前缀
     */
    public static final String BLACKLIST_PREFIX = BASIC_PREFIX + "blacklist:";

    /**
     * 邮箱验证码邮件参数名
     */
    public static final String EMAIL_PARAMETER = "email";

    /**
     * 验证码登录时验证码缓存的前缀
     */
    public static final String CAPTCHA_KEY_PREFIX = "basic:captcha:";

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
