package com.basic.domain.request;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Schema(title = "DineTableBindingRequest", description = "订单桌位绑定请求参数")
public class DineTableBindingRequest {

    @NotNull(message = "桌台ID不能为空")
    @Schema(title = "桌台ID", description = "桌台ID")
    private Long tableId;

    @Schema(title = "用户ID", description = "用户ID（创建时以后端登录用户为准）")
    private Long userId;

    @Schema(title = "状态", description = "N-解绑 Y-绑定中")
    private StatusEnum status;

    @Schema(title = "绑定时间", description = "绑定时间（创建时以后端时间为准）")
    private Date bindTime;

    @Schema(title = "解绑时间", description = "解绑时间（由后端维护）")
    private Date unbindTime;
}
