package com.basic.domain.entity;

import com.basic.enums.SelectTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 属性组表
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "dine_attribute_group")
public class DineAttributeGroup extends BasicEntity {

    /**
     * 属性组ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 所属门店ID
     */
    private Long storeId;

    /**
     * 组名，如"温度"
     */
    private String name;

    /**
     * 1-单选 2-多选
     */
    private SelectTypeEnum selectType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * N-停用 Y-启用
     */
    private StatusEnum status;
}