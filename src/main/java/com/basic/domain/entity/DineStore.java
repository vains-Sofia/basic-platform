package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.StatusEnum;
import com.basic.handler.mybatis.StringListTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 门店表
 *
 * @author vains
 */
@Data
@TableName(value = "dine_store", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
public class DineStore extends BasicEntity {

    /**
     * 门店ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 门店名称
     */
    private String name;

    /**
     * Logo URL
     */
    private String logo;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 商家相册
     */
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> albums;

    /**
     * 营业时间设置
     */
    private String businessHours;

    /**
     * 门店描述
     */
    private String description;

    /**
     * N-停业 Y-营业中
     */
    private StatusEnum status;

}