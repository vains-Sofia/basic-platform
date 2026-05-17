package com.basic.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "DineDishAttributeOptionRequest", description = "菜品-选项精细化配置请求参数")
public class DineDishAttributeOptionRequest {

    @NotNull(message = "菜品ID不能为空")
    @Schema(title = "菜品ID", description = "菜品ID")
    private Long dishId;

    @NotNull(message = "选项ID不能为空")
    @Schema(title = "选项ID", description = "选项ID")
    private Long optionId;

    @Schema(title = "是否可用", description = "该菜品是否可用此选项")
    private Boolean isAvailable;

    @Schema(title = "覆盖价格调整", description = "覆盖价格调整，NULL则使用选项默认值")
    private Integer priceAdjustmentOverride;
}