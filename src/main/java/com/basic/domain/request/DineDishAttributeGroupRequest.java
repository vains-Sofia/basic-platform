package com.basic.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "DineDishAttributeGroupRequest", description = "菜品-属性组关联请求参数")
public class DineDishAttributeGroupRequest {

    @NotNull(message = "菜品ID不能为空")
    @Schema(title = "菜品ID", description = "菜品ID")
    private Long dishId;

    @NotNull(message = "属性组ID不能为空")
    @Schema(title = "属性组ID", description = "属性组ID")
    private Long groupId;

    @Schema(title = "是否必选", description = "是否必选，数据库存储为 TINYINT(0/1)")
    private Boolean required;

    @Schema(title = "排序", description = "该组在详情页的排序")
    private Integer sort;
}