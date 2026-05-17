package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 桌台绑定记录
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "dine_table_binding")
public class DineTableBinding extends BasicEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 桌台ID
     */
    private Long tableId;

    /**
     * 用户ID（系统用户表）
     */
    private Long userId;

    /**
     * N-解绑 Y-绑定中
     */
    private StatusEnum status;

    /**
     *
     */
    private LocalDateTime bindTime;

    /**
     *
     */
    private LocalDateTime unbindTime;

}