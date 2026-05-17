package com.basic.domain.response;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(name = "FindDineDishResponse", description = "响应对象-菜品")
public class FindDineDishResponse {

    @Schema(title = "菜品ID", description = "菜品ID")
    private Long id;

    @Schema(title = "所属门店ID", description = "所属门店ID")
    private Long storeId;

    @Schema(title = "所属分类ID", description = "所属分类ID")
    private Long categoryId;

    @Schema(title = "菜品名称", description = "菜品名称")
    private String name;

    @Schema(title = "主图URL", description = "主图URL")
    private String image;

    @Schema(title = "多图URL数组", description = "多图URL数组")
    private List<String> images;

    @Schema(title = "简要描述", description = "简要描述")
    private String description;

    @Schema(title = "基准价格(分)", description = "基准价格(分)")
    private Integer price;

    @Schema(title = "标签", description = "标签，如'招牌,限时'")
    private List<String> labels;

    @Schema(title = "是否推荐", description = "0-普通 1-推荐")
    private Integer recommend;

    @Schema(title = "预计制作时长(秒)", description = "预计制作时长(秒)")
    private Integer cookingTime;

    @Schema(title = "单位", description = "单位")
    private String unit;

    @Schema(title = "累计销量", description = "累计销量")
    private Integer sales;

    @Schema(title = "排序(升序)", description = "排序(升序)")
    private Integer sort;

    @Schema(title = "状态", description = "N-停用 Y-启用")
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