package com.basic.domain.request;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "DineAttributeOptionRequest", description = "订单属性选项请求参数")
public class DineAttributeOptionRequest {

    @NotNull(message = "属性组ID不能为空")
    @Schema(title = "所属属性组ID", description = "所属属性组ID")
    private Long groupId;

    @NotNull(message = "选项名不能为空")
    @Schema(title = "选项名", description = "选项名，如'冰'")
    private String name;

    @Schema(title = "价格调整(分)", description = "价格调整(分)，可为负数")
    private Integer priceAdjustment;

    @Schema(title = "排序", description = "排序")
    private Integer sort;

    @Schema(title = "状态", description = "N-停用 Y-启用")
    private StatusEnum status;
}