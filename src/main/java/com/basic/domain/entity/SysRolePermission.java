package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限关联表
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_permission")
public class SysRolePermission extends BasicEntity {

    /**
     * 角色菜单关联表 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 权限菜单 ID
     */
    private Long permissionId;
}