package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型表
 *
 * @author vains
 */
@Data
@TableName(value = "sys_dict_type")
@EqualsAndHashCode(callSuper = true)
public class SysDictType extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典类型编码
     */
    private String typeCode;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 类型说明
     */
    private String description;

    /**
     * 状态（Y=启用，N=禁用）
     */
    private StatusEnum status;
}