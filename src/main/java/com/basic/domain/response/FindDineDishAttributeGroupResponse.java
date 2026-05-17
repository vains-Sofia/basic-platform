package com.basic.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "FindDineDishAttributeGroupResponse", description = "响应对象-菜品-属性组关联")
public class FindDineDishAttributeGroupResponse {

    @Schema(title = "主键ID", description = "主键ID")
    private Long id;

    @Schema(title = "菜品ID", description = "菜品ID")
    private Long dishId;

    @Schema(title = "属性组ID", description = "属性组ID")
    private Long groupId;

    @Schema(title = "是否必选", description = "是否必选，数据库存储为 TINYINT(0/1)")
    private Boolean required;

    @Schema(title = "排序", description = "该组在详情页的排序")
    private Integer sort;

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