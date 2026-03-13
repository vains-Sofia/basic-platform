package com.basic.domain.response;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 响应对象：字典类型
 * <p>
 * 用于返回字典类型的详细信息，包括ID、类型代码、名称、描述、状态等。
 * </p>
 * <p>
 * 注意：此类是一个简单的响应对象，通常用于API响应中。
 * </p>
 *
 * @author vains
 */
@Data
@Schema(name = "FindSysDictTypeResponse", description = "响应对象-字典类型")
public class FindSysDictTypeResponse {

    @Schema(title = "字典类型 ID", description = "字典类型的唯一标识符")
    private Long id;

    @Schema(title = "字典类型代码", description = "字典类型的代码，用于标识不同的字典类型")
    private String typeCode;

    @Schema(title = "字典类型名称", description = "字典类型的名称，用于描述该字典类型")
    private String name;

    @Schema(title = "字典类型描述", description = "对字典类型的详细描述")
    private String description;

    @Schema(title = "状态", description = "字典类型的状态，状态（Y=启用，N=禁用）")
    private StatusEnum status;

    @Schema(title = "创建人", description = "创建该字典类型的用户 ID")
    private Long createBy;

    @Schema(title = "创建人姓名", description = "创建该字典类型的用户姓名")
    private String createName;

    @Schema(title = "创建时间", description = "字典类型的创建时间")
    private LocalDateTime createTime;

    @Schema(title = "修改人", description = "最后修改该字典类型的用户 ID")
    private Long updateBy;

    @Schema(title = "修改人姓名", description = "最后修改该字典类型的用户姓名")
    private String updateName;

    @Schema(title = "修改时间", description = "字典类型的最后修改时间")
    private LocalDateTime updateTime;

}