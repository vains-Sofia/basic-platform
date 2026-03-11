package com.basic.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 修改用户角色入参
 *
 * @author vains
 */
@Data
@Schema(name = "UpdateUserRolesRequest", description = "修改用户角色入参")
public class UpdateUserRolesRequest implements Serializable {

    @NotNull
    @Schema(title = "用户 id", description = "用户 id")
    private Long userId;

    @Schema(title = "角色 id列表", description = "角色 id列表")
    private List<Long> roleIds;

}
