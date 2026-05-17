package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.RecommendEnum;
import com.basic.enums.StatusEnum;
import com.basic.handler.mybatis.StringListTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 菜品表
 *
 * @author vains
 */
@Data
@TableName(value = "dine_dish", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
public class DineDish extends BasicEntity {

    /**
     * 菜品ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 所属门店ID
     */
    private Long storeId;

    /**
     * 所属分类ID
     */
    private Long categoryId;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 主图URL
     */
    private String image;

    /**
     * 多图URL数组
     */
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> images;

    /**
     * 简要描述
     */
    private String description;

    /**
     * 基准价格(分)
     */
    private Integer price;

    /**
     * 标签，如"招牌,限时"
     */
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> labels;

    /**
     * 0-普通 1-推荐
     */
    private RecommendEnum recommend;

    /**
     * 预计制作时长(秒)
     */
    private Integer cookingTime;

    /**
     * 单位
     */
    private String unit;

    /**
     * 累计销量
     */
    private Integer sales;

    /**
     * N-停用 Y-启用
     */
    private StatusEnum status;

    /**
     * 排序(升序)
     */
    private Integer sort;
}