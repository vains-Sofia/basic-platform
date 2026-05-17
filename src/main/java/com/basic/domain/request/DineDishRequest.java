package com.basic.domain.request;

import com.basic.enums.RecommendEnum;
import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "DineDishRequest", description = "菜品请求参数")
public class DineDishRequest {

    @NotNull(message = "门店ID不能为空")
    @Schema(title = "所属门店ID", description = "所属门店ID")
    private Long storeId;

    @NotNull(message = "分类ID不能为空")
    @Schema(title = "所属分类ID", description = "所属分类ID")
    private Long categoryId;

    @NotNull(message = "菜品名称不能为空")
    @Schema(title = "菜品名称", description = "菜品名称")
    private String name;

    @Schema(title = "主图URL", description = "主图URL")
    private String image;

    @Schema(title = "多图URL数组", description = "多图URL数组")
    private List<String> images;

    @Schema(title = "简要描述", description = "简要描述")
    private String description;

    @NotNull(message = "基准价格不能为空")
    @Schema(title = "基准价格(分)", description = "基准价格(分)")
    private Integer price;

    @Schema(title = "标签", description = "标签，如'招牌,限时'")
    private List<String> labels;

    @Schema(title = "是否推荐", description = "0-普通 1-推荐")
    private RecommendEnum recommend;

    @Schema(title = "预计制作时长(秒)", description = "预计制作时长(秒)")
    private Integer cookingTime;

    @Schema(title = "单位", description = "单位")
    private String unit;

    @Schema(title = "排序(升序)", description = "排序(升序)")
    private Integer sort;

    @Schema(title = "状态", description = "N-停用 Y-启用")
    private StatusEnum status;
}