package com.basic.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 桌台状态
 * 0-空闲 1-占用 2-留座
 *
 * @author vains
 */
@Getter
@RequiredArgsConstructor
public enum TableStatusEnum implements BasicEnum<Integer, TableStatusEnum> {

    IDLE(0, "空闲"),

    OCCUPY(1, "占用"),

    RESERVE_A_SEAT(2, "留座");


    /**
     * 桌台状态
     */
    private final Integer status;

    /**
     * 状态描述
     */
    private final String desc;


    @Override
    public Integer getValue() {
        return this.status;
    }
}
