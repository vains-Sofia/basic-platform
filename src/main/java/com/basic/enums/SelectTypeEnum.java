package com.basic.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 选择类型枚举
 * 1-单选 2-多选
 *
 * @author vains
 */
@Getter
@RequiredArgsConstructor
public enum SelectTypeEnum implements BasicEnum<Integer, SelectTypeEnum> {

    /**
     * 单选
     */
    SINGLE(1, "单选"),

    /**
     * 多选
     */
    MULTIPLE(2, "多选");

    /**
     * 选择类型值
     */
    private final Integer type;

    /**
     * 类型描述
     */
    private final String desc;

    @Override
    public Integer getValue() {
        return this.type;
    }
}