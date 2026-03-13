package com.basic.domain.request;

import com.basic.domain.BasicPageable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型分页查询请求参数
 *
 * @author vains
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SysDictTypePageRequest", description = "字典类型分页查询请求参数")
public class SysDictTypePageRequest extends BasicPageable {

    @Schema(title = "模糊查询 itemKey 或 itemValue", description = "模糊查询 itemKey 或 itemValue")
    private String keyword;

}
