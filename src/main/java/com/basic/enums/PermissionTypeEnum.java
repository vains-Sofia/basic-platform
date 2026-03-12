package com.basic.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 权限类型枚举
 *
 * @author vains
 */
@Getter
@RequiredArgsConstructor
public enum PermissionTypeEnum implements BasicEnum<Integer, PermissionTypeEnum> {

    /**
     * 菜单
     */
    MENU(0, "菜单"),

    /**
     * iframe
     */
    IFRAME(1, "iframe"),

    /**
     * 外链
     */
    EXTERNAL_LINK(2, "外链"),

    /**
     * 接口
     */
    REST(3, "接口");

    /**
     * 权限类型值
     */
    private final Integer type;

    /**
     * 权限类型名
     */
    private final String name;

    @Override
    public Integer getValue() {
        return this.type;
    }

    /**
     * 是否是菜单类型
     *
     * @param permissionType 权限类型
     * @return true 是菜单类型，false 不是菜单类型
     */
    public static boolean isMenuType(PermissionTypeEnum permissionType) {
        if (permissionType == null) {
            return false;
        }
        return MENU.equals(permissionType) || IFRAME.equals(permissionType) || EXTERNAL_LINK.equals(permissionType);
    }
}
