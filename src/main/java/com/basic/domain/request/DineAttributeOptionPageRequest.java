package com.basic.domain.request;

import com.basic.domain.BasicPageable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DineAttributeOptionPageRequest", description = "订单属性选项分页查询请求参数")
public class DineAttributeOptionPageRequest extends BasicPageable {

    @Schema(title = "模糊查询名称", description = "模糊查询选项名")
    private String keyword;

    @Schema(title = "属性组ID", description = "按属性组ID筛选")
    private Long groupId;
}