package com.basic.domain.request;

import com.basic.domain.BasicPageable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DineAttributeGroupPageRequest", description = "订单属性组分页查询请求参数")
public class DineAttributeGroupPageRequest extends BasicPageable {

    @Schema(title = "模糊查询名称", description = "模糊查询组名")
    private String keyword;

    @Schema(title = "门店ID", description = "按门店ID筛选")
    private Long storeId;
}