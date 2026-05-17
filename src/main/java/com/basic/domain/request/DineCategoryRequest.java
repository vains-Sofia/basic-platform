package com.basic.domain.request;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "DineCategoryRequest", description = "菜品分类请求参数")
public class DineCategoryRequest {

    @NotNull(message = "门店ID不能为空")
    @Schema(title = "所属门店ID", description = "所属门店ID")
    private Long storeId;

    @NotNull(message = "分类名称不能为空")
    @Schema(title = "分类名称", description = "分类名称")
    private String name;

    @Schema(title = "排序值(升序)", description = "排序值(升序)")
    private Integer sort;

    @Schema(title = "状态", description = "N-停用 Y-启用")
    private StatusEnum status;
}