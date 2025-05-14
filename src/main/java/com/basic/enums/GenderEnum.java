package com.basic.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 性别枚举
 * 依照2003年中国国家质量监督检验检疫总局发布的国家标准 GB/T 2261.1-2003《个人基本信息分类与代码 第1部分：人的性别代码》，人的性别信息代码包括：
 * 0未知的性别、1男性、2女性、9未说明的性别
 *
 * @author vains
 */
@Getter
@RequiredArgsConstructor
public enum GenderEnum implements BasicEnum<Integer, GenderEnum> {

    /**
     * 0-未知性别
     */
    UNKNOWN_GENDER(0, "未知性别"),

    /**
     * 1-男性
     */
    MALE(1, "男性"),

    /**
     * 2-女性
     */
    FEMALE(2, "女性"),

    /**
     * 9-未说明的性别
     */
    UNSPECIFIED_GENDER(9, "未说明的性别");

    /**
     * 性别值
     */
    private final Integer gender;

    /**
     * 性别名
     */
    private final String name;

    @Override
    public Integer getValue() {
        return this.gender;
    }
}
