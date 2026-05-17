package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜品-属性组关联
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "dine_dish_attribute_group")
public class DineDishAttributeGroup extends BasicEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜品ID
     */
    private Long dishId;

    /**
     * 属性组ID
     */
    private Long groupId;

    /**
     * 是否必选
     */
    private Boolean required;

    /**
     * 该组在详情页的排序
     */
    private Integer sort;

}