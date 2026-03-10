package com.basic.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 状态枚举
 * 状态（Y=启用，N=禁用）
 *
 * @author vains
 */
@Getter
@RequiredArgsConstructor
public enum StatusEnum implements BasicEnum<String, StatusEnum> {

    /**
     * 启用
     */
    ENABLE("Y", "启用"),

    /**
     * 禁用
     */
    DISABLE("N", "禁用");

    /**
     * 状态值
     */
    private final String code;

    /**
     * 状态描述
     */
    private final String desc;

    @Override
    public String getValue() {
        return this.code;
    }
}
