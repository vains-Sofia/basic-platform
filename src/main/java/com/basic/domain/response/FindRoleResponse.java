package com.basic.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 查询角色响应
 *
 * @author vains
 */
@Data
@Schema(name = "FindRoleResponse", description = "查询角色响应")
public class FindRoleResponse implements Serializable {

    /**
     * 主键 id
     */
    @Schema(title = "主键 id", description = "主键 id")
    private Long id;

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

    /**
     * 是否已删除
     */
    @Schema(title = "是否已删除", description = "是否已删除")
    private Integer deleted;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "修改人名称")
    private String updateName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

}
