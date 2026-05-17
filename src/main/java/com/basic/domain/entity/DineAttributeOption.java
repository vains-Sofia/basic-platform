package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 属性选项表
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "dine_attribute_option")
public class DineAttributeOption extends BasicEntity {

    /**
     * 选项ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 所属属性组ID
     */
    private Long groupId;

    /**
     * 选项名，如"冰"
     */
    private String name;

    /**
     * 价格调整(分)，可为负数
     */
    private Integer priceAdjustment;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * N-停用 Y-启用
     */
    private StatusEnum status;
}