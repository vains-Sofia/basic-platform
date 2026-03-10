package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RBAC 角色表
 * @author vains
 */
@Data
@TableName(value ="sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BasicEntity {

    /**
     * 主键 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 是否已删除
     */
    @TableLogic
    private Boolean deleted;

}