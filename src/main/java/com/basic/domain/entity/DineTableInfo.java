package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.TableStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 桌台表
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "dine_table_info")
public class DineTableInfo extends BasicEntity {

    /**
     * 桌台ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 所属门店ID
     */
    private Long storeId;

    /**
     * 桌台名称，如"A01"
     */
    private String name;

    /**
     * 二维码标识，全局唯一
     */
    private String code;

    /**
     * 建议容纳人数
     */
    private Integer capacity;

    /**
     * 0-空闲 1-占用 2-留座
     */
    private TableStatusEnum tableStatus;

}