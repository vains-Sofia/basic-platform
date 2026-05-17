package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜品分类表
 *
 * @author vains
 */
@Data
@TableName(value = "dine_category")
@EqualsAndHashCode(callSuper = true)
public class DineCategory extends BasicEntity {

    /**
     * 分类ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 所属门店ID
     */
    private Long storeId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 排序值(升序)
     */
    private Integer sort;

    /**
     * N-停用 Y-启用
     */
    private StatusEnum status;
}