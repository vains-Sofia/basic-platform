package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典项表
 *
 * @author vains
 */
@Data
@TableName(value = "sys_dict_item")
@EqualsAndHashCode(callSuper = true)
public class SysDictItem extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典类型编码（外键）
     */
    private String typeCode;

    /**
     * 字典项键
     */
    private String itemCode;

    /**
     * 字典项值
     */
    private String itemName;

    /**
     * 排序值
     */
    private Integer sortOrder;

    /**
     * 状态（Y=启用，N=禁用）
     */
    private StatusEnum status;

    /**
     * 多语言 JSON 值
     */
    private String i18nJson;
}