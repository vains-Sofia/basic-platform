package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 门店表
 *
 * @author vains
 */
@Data
@TableName(value = "dine_store")
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
     * N-停业 Y-营业中
     */
    private StatusEnum status;

}