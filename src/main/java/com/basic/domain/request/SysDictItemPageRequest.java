package com.basic.domain.request;

import com.basic.domain.BasicPageable;
import com.basic.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典项分页查询请求参数
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SysDictItemPageRequest", description = "字典项分页查询请求参数")
public class SysDictItemPageRequest extends BasicPageable {

    @Schema(title = "字典类型编码", description = "字典类型的唯一编码")
    private String typeCode;

    @Schema(title = "模糊查询 itemKey 或 itemValue", description = "模糊查询 itemKey 或 itemValue")
    private String keyword;

    @Schema
    private StatusEnum status;
}
