package com.basic.domain.request;

import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 字典类型请求参数
 *
 * @author vains
 */
@Data
@Schema(title = "SysDictTypeRequest", description = "字典类型请求参数")
public class SysDictTypeRequest {

    @NotBlank(message = "字典类型编码不能为空")
    @Schema(title = "字典类型编码", description = "字典类型编码")
    private String typeCode;

    @NotBlank(message = "字典名称不能为空")
    @Schema(title = "字典名称", description = "字典名称")
    private String name;

    @Schema(title = "字典类型描述", description = "字典类型描述")
    private String description;

    @Schema
    private StatusEnum status = StatusEnum.ENABLE;
}