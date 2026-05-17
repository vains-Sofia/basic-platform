package com.basic.domain.request;

import com.basic.enums.TableStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "DineTableInfoRequest", description = "订单桌位信息请求参数")
public class DineTableInfoRequest {

    @NotNull(message = "门店ID不能为空")
    @Schema(title = "所属门店ID", description = "所属门店ID")
    private Long storeId;

    @NotNull(message = "桌台名称不能为空")
    @Schema(title = "桌台名称", description = "桌台名称，如'A01'")
    private String name;

    @Schema(title = "二维码标识", description = "二维码标识，全局唯一")
    private String code;

    @Schema(title = "建议容纳人数", description = "建议容纳人数")
    private Integer capacity;

    @Schema(title = "桌台状态", description = "0-空闲 1-占用 2-留座")
    private TableStatusEnum tableStatus;
}