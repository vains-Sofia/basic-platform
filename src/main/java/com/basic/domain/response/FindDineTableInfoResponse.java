package com.basic.domain.response;

import com.basic.enums.TableStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "FindDineTableInfoResponse", description = "响应对象-订单桌位信息")
public class FindDineTableInfoResponse {

    @Schema(title = "桌台ID", description = "桌台ID")
    private Long id;

    @Schema(title = "所属门店ID", description = "所属门店ID")
    private Long storeId;

    @Schema(title = "桌台名称", description = "桌台名称，如'A01'")
    private String name;

    @Schema(title = "二维码标识", description = "二维码标识，全局唯一")
    private String code;

    @Schema(title = "建议容纳人数", description = "建议容纳人数")
    private Integer capacity;

    @Schema(title = "桌台状态", description = "0-空闲 1-占用 2-留座")
    private TableStatusEnum tableStatus;

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