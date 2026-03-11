package com.basic.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 查询角色入参
 *
 * @author vains
 */
@Data
@Schema(name = "FindRoleRequest", description = "查询角色信息入参")
public class FindRoleRequest {

    /**
     * 角色代码
     */
    @Schema(title = "角色代码", description = "角色代码")
    private String code;

    /**
     * 角色名称
     */
    @Schema(title = "角色名称", description = "角色名称")
    private String name;

    /**
     * 角色描述
     */
    @Schema(title = "角色描述", description = "角色描述")
    private String description;

}
