package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联表
 *
 * @author vains
 */
@Data
@TableName(value = "sys_user_role")
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends BasicEntity {

    /**
     * 主键 id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 用户 ID
     */
    private Long userId;

}