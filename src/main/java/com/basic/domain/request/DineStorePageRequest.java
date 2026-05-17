package com.basic.domain.request;

import com.basic.domain.BasicPageable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DineStorePageRequest", description = "店铺信息分页查询请求参数")
public class DineStorePageRequest extends BasicPageable {

    @Schema(title = "模糊查询名称", description = "模糊查询门店名称")
    private String keyword;

    @Schema(title = "状态", description = "N-停业 Y-营业中")
    private String status;
}