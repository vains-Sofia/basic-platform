package com.basic.domain.request;

import com.basic.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.io.Serializable;

/**
 * 保存或修改角色信息入参
 *
 * @author vains
 */
@Data
@Schema(name = "SaveRoleRequest", description = "保存或修改角色信息入参")
public class SaveRoleRequest implements Serializable {

    /**
     * 主键 id
     */
    @NotNull(groups = Update.class)
    @Schema(title = "主键 id", description = "主键 id")
    private Long id;

    /**
     * 角色代码
     */
    @NotBlank(groups = {Update.class, Default.class})
    @Schema(title = "角色代码", description = "角色代码")
    private String code;

    /**
     * 角色名称
     */
    @NotBlank(groups = {Update.class, Default.class})
    @Schema(title = "角色名称", description = "角色名称")
    private String name;

    /**
     * 角色描述
     */
    @Schema(title = "角色描述", description = "角色描述")
    private String description;

}
