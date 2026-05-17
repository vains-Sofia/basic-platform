package com.basic.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 推荐状态枚举
 * 0-普通 1-推荐
 *
 * @author vains
 */
@Getter
@RequiredArgsConstructor
public enum RecommendEnum implements BasicEnum<Integer, RecommendEnum> {

    /**
     * 普通
     */
    NORMAL(0, "普通"),

    /**
     * 推荐
     */
    RECOMMEND(1, "推荐");

    /**
     * 推荐状态值
     */
    private final Integer type;

    /**
     * 状态描述
     */
    private final String desc;

    @Override
    public Integer getValue() {
        return this.type;
    }
}