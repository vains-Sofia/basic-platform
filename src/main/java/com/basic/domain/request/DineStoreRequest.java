package com.basic.domain.request;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "DineStoreRequest", description = "店铺信息请求参数")
public class DineStoreRequest {

    @NotNull(message = "门店名称不能为空")
    @Schema(title = "门店名称", description = "门店名称")
    private String name;

    @Schema(title = "Logo URL", description = "Logo URL")
    private String logo;

    @Schema(title = "状态", description = "N-停业 Y-营业中")
    private StatusEnum status;
}