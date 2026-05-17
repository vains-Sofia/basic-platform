package com.basic.domain.response;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "FindDineAttributeOptionResponse", description = "响应对象-订单属性选项")
public class FindDineAttributeOptionResponse {

    @Schema(title = "选项ID", description = "选项ID")
    private Long id;

    @Schema(title = "所属属性组ID", description = "所属属性组ID")
    private Long groupId;

    @Schema(title = "选项名", description = "选项名，如'冰'")
    private String name;

    @Schema(title = "价格调整(分)", description = "价格调整(分)，可为负数")
    private Integer priceAdjustment;

    @Schema(title = "排序", description = "排序")
    private Integer sort;

    @Schema(title = "状态", description = "N-停用 Y-启用")
    private StatusEnum status;

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