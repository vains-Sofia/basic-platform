package com.basic.domain.response;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "FindDineAttributeGroupResponse", description = "响应对象-属性组")
public class FindDineAttributeGroupResponse {

    @Schema(title = "属性组ID", description = "属性组ID")
    private Long id;

    @Schema(title = "所属门店ID", description = "所属门店ID")
    private Long storeId;

    @Schema(title = "组名", description = "组名，如'温度'")
    private String name;

    @Schema(title = "选择类型", description = "1-单选 2-多选")
    private Integer selectType;

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