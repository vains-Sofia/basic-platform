package com.basic.domain.request;

import com.basic.domain.BasicPageable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DineTableBindingPageRequest", description = "订单桌位绑定分页查询请求参数")
public class DineTableBindingPageRequest extends BasicPageable {

    @Schema(title = "桌台ID", description = "按桌台ID筛选")
    private Long tableId;

    @Schema(title = "用户ID", description = "按用户ID筛选")
    private Long userId;

    @Schema(title = "状态", description = "N-解绑 Y-绑定中")
    private String status;
}