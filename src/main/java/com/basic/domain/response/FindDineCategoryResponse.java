package com.basic.domain.response;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "FindDineCategoryResponse", description = "响应对象-菜品分类")
public class FindDineCategoryResponse {

    @Schema(title = "分类ID", description = "分类ID")
    private Long id;

    @Schema(title = "所属门店ID", description = "所属门店ID")
    private Long storeId;

    @Schema(title = "分类名称", description = "分类名称")
    private String name;

    @Schema(title = "排序值(升序)", description = "排序值(升序)")
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