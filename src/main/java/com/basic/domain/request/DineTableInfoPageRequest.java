package com.basic.domain.request;

import com.basic.domain.BasicPageable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DineTableInfoPageRequest", description = "订单桌位信息分页查询请求参数")
public class DineTableInfoPageRequest extends BasicPageable {

    @Schema(title = "模糊查询名称", description = "模糊查询桌台名称")
    private String keyword;

    @Schema(title = "门店ID", description = "按门店ID筛选")
    private Long storeId;

    @Schema(title = "桌台状态", description = "0-空闲 1-占用 2-留座")
    private Integer tableStatus;
}