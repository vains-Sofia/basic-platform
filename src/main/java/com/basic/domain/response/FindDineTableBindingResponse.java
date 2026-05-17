package com.basic.domain.response;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Schema(name = "FindDineTableBindingResponse", description = "响应对象-订单桌位绑定")
public class FindDineTableBindingResponse {

    @Schema(title = "主键ID", description = "主键ID")
    private Long id;

    @Schema(title = "桌台ID", description = "桌台ID")
    private Long tableId;

    @Schema(title = "用户ID", description = "用户ID（系统用户表）")
    private Long userId;

    @Schema(title = "状态", description = "N-解绑 Y-绑定中")
    private StatusEnum status;

    @Schema(title = "绑定时间", description = "绑定时间")
    private Date bindTime;

    @Schema(title = "解绑时间", description = "解绑时间")
    private Date unbindTime;

    @Schema(title = "创建人", description = "创建人用户ID")
    private Long createBy;

    @Schema(title = "创建人姓名", description = "创建人姓名")
    private String createName;

    @Schema(title = "创建时间", description = "创建时间")
    private LocalDateTime createTime;

    @Schema(title = "修改人", description = "修改人用户ID")
    private Long updateBy;

    @Schema(title = "修改人姓名", description = "修改人姓名")
    private String updateName;

    @Schema(title = "修改时间", description = "修改时间")
    private LocalDateTime updateTime;
}