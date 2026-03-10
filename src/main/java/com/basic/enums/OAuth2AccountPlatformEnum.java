package com.basic.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * oauth2三方登录类型
 *
 * @author vains
 */
@RequiredArgsConstructor
public enum OAuth2AccountPlatformEnum implements BasicEnum<String, OAuth2AccountPlatformEnum> {

    /**
     * 微信登录
     */
    WECHAT("wechat", "微信登录"),

    /**
     * gitee提供的OAuth2登录
     */
    GITEE("gitee", "gitee提供的OAuth2登录"),

    /**
     * github提供的OAuth2登录
     */
    GITHUB("github", "github提供的OAuth2登录"),

    /**
     * 自有系统用户
     */
    SYSTEM("system", "自有系统用户");

    /**
     * 登录类型
     */
    private final String loginType;

    /**
     * 类型说明
     */
    @Getter
    private final String description;

    @Override
    public String getValue() {
        return this.loginType;
    }
}
