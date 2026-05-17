package com.basic.domain.request;

import com.basic.enums.SelectTypeEnum;
import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "DineAttributeGroupRequest", description = "订单属性组请求参数")
public class DineAttributeGroupRequest {

    @NotNull(message = "门店ID不能为空")
    @Schema(title = "所属门店ID", description = "所属门店ID")
    private Long storeId;

    @NotNull(message = "组名不能为空")
    @Schema(title = "组名", description = "组名，如'温度'")
    private String name;

    @Schema(title = "选择类型", description = "1-单选 2-多选")
    private SelectTypeEnum selectType;

    @Schema(title = "排序", description = "排序")
    private Integer sort;

    @Schema(title = "状态", description = "N-停用 Y-启用")
    private StatusEnum status;
}