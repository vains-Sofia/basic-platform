package com.basic.domain.response;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 响应对象：字典项
 * <p>
 * 用于返回字典项的详细信息，包括ID、类型代码、键、值、排序、状态等。
 * </p>
 * <p>
 * 注意：此类是一个简单的响应对象，通常用于API响应中。
 * </p>
 *
 * @author vains
 */
@Data
@Schema(title = "FindSysDictItemResponse", description = "字典项响应对象")
public class FindSysDictItemResponse implements Serializable {

    @Schema(title = "字典项 ID", description = "字典项的唯一标识符")
    private Long id;

    @Schema(title = "字典类型代码", description = "字典项所属的字典类型代码")
    private String typeCode;

    @Schema(title = "字典项编码", description = "字典项的键，用于标识该字典项")
    private String itemCode;

    @Schema(title = "字典项名称", description = "字典项的名称，用于描述该字典项")
    private String itemName;

    @Schema(title = "字典项排序", description = "字典项的排序值，用于控制显示顺序")
    private Integer sortOrder;

    @Schema(title = "状态", description = "字典项的状态（Y=启用，N=禁用）")
    private StatusEnum status;

    @Schema(title = "多语言 JSON 值", description = "字典项的多语言支持 JSON 字符串")
    private String i18nJson;

    @Schema(title = "创建人", description = "创建该字典项的用户ID")
    private Long createBy;

    @Schema(title = "创建人姓名", description = "创建该字典项的用户姓名")
    private String createName;

    @Schema(title = "创建时间", description = "字典项的创建时间")
    private LocalDateTime createTime;

    @Schema(title = "修改人", description = "最后修改该字典项的 用户ID")
    private Long updateBy;

    @Schema(title = "修改人姓名", description = "最后修改该字典项的用户姓名")
    private String updateName;

    @Schema(title = "修改时间", description = "字典项的最后修改时间")
    private LocalDateTime updateTime;
}
