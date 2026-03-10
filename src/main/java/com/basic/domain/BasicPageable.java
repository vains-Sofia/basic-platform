package com.basic.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求入参
 *
 * @author vains
 */
@Data
@Schema(title = "分页请求入参")
public abstract class BasicPageable implements Serializable {

    /**
     * 当前页码
     */
    @Min(1)
    @NotNull
    @Schema(title = "当前页码", description = "当前页码")
    private Long current;

    /**
     * 每页行数
     */
    @Min(1)
    @NotNull
    @Schema(title = "每页行数", description = "每页行数")
    private Long size;

}
