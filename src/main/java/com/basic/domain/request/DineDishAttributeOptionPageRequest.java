package com.basic.domain.request;

import com.basic.domain.BasicPageable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DineDishAttributeOptionPageRequest", description = "菜品-选项精细化配置分页查询请求参数")
public class DineDishAttributeOptionPageRequest extends BasicPageable {

    @Schema(title = "菜品ID", description = "按菜品ID筛选")
    private Long dishId;

    @Schema(title = "选项ID", description = "按选项ID筛选")
    private Long optionId;
}