package com.basic.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "FindDineDishAttributeOptionResponse", description = "响应对象-菜品-选项精细化配置")
public class FindDineDishAttributeOptionResponse {

    @Schema(title = "主键ID", description = "主键ID")
    private Long id;

    @Schema(title = "菜品ID", description = "菜品ID")
    private Long dishId;

    @Schema(title = "选项ID", description = "选项ID")
    private Long optionId;

    @Schema(title = "是否可用", description = "该菜品是否可用此选项")
    private Boolean isAvailable;

    @Schema(title = "覆盖价格调整", description = "覆盖价格调整，NULL则使用选项默认值")
    private Integer priceAdjustmentOverride;

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