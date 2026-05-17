package com.basic.domain.request;

import com.basic.domain.BasicPageable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DineDishAttributeGroupPageRequest", description = "菜品-属性组关联分页查询请求参数")
public class DineDishAttributeGroupPageRequest extends BasicPageable {

    @Schema(title = "菜品ID", description = "按菜品ID筛选")
    private Long dishId;

    @Schema(title = "属性组ID", description = "按属性组ID筛选")
    private Long groupId;
}