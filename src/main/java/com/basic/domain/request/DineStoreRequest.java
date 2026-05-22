package com.basic.domain.request;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(title = "DineStoreRequest", description = "店铺信息请求参数")
public class DineStoreRequest {

    @NotNull(message = "门店名称不能为空")
    @Schema(title = "门店名称", description = "门店名称")
    private String name;

    @Schema(title = "Logo URL", description = "Logo URL")
    private String logo;

    @Schema(title = "门店地址", description = "门店地址")
    private String address;

    @Schema(title = "经度", description = "经度")
    private BigDecimal longitude;

    @Schema(title = "纬度", description = "纬度")
    private BigDecimal latitude;

    @Schema(title = "商家相册", description = "商家相册 URL 数组")
    private List<String> albums;

    @Schema(title = "营业时间设置", description = "营业时间设置")
    private String businessHours;

    @Schema(title = "门店描述", description = "门店描述")
    private String description;

    @Schema(title = "状态", description = "N-停业 Y-营业中")
    private StatusEnum status;
}