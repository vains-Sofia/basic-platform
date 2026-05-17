package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜品-选项精细化配置
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "dine_dish_attribute_option")
public class DineDishAttributeOption extends BasicEntity {

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
     * 选项ID
     */
    private Long optionId;

    /**
     * 该菜品是否可用此选项
     */
    private Boolean isAvailable;

    /**
     * 覆盖价格调整，NULL则使用选项默认值
     */
    private Integer priceAdjustmentOverride;

}