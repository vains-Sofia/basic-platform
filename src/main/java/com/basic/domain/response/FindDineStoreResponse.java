package com.basic.domain.response;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(name = "FindDineStoreResponse", description = "响应对象-店铺信息")
public class FindDineStoreResponse {

    @Schema(title = "门店ID", description = "门店ID")
    private Long id;

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