package com.basic.controller.system;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.request.FindRolePageRequest;
import com.basic.domain.request.FindRoleRequest;
import com.basic.domain.request.SaveRoleRequest;
import com.basic.domain.request.UpdateRolePermissionsRequest;
import com.basic.domain.response.FindRoleResponse;
import com.basic.service.SysRoleService;
import com.basic.validation.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RBAC 角色相关接口
 *
 * @author vains
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
@Tag(name = "RBAC 角色相关接口", description = "RBAC 角色相关接口")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @GetMapping("/findByPage")
    @Operation(summary = "分页查询角色信息列表", description = "分页查询角色信息列表")
    public Result<PageResult<FindRoleResponse>> findByPage(@Valid FindRolePageRequest request) {
        PageResult<FindRoleResponse> pageResult = sysRoleService.findByPage(request);
        return Result.success(pageResult);
    }

    @GetMapping("/roleDetails/{id}")
    @Parameter(name = "id", description = "角色id")
    @Operation(summary = "查询角色详情", description = "根据角色 id查询角色详情")
    public Result<FindRoleResponse> roleDetails(@NotNull @PathVariable Long id) {
        FindRoleResponse roleResponse = sysRoleService.roleDetails(id);
        return Result.success(roleResponse);
    }

    @PostMapping("/insertRole")
    @Operation(summary = "添加角色信息", description = "添加角色信息")
    public Result<String> insertRole(@Validated @RequestBody SaveRoleRequest request) {
        // 置空id，防止插入变修改
        request.setId(null);
        sysRoleService.saveRole(request);
        return Result.success();
    }

    @PutMapping("/updateRole")
    @Operation(summary = "修改角色信息", description = "修改角色信息")
    public Result<String> updateRole(@Validated(Update.class) @RequestBody SaveRoleRequest request) {
        sysRoleService.saveRole(request);
        return Result.success();
    }

    @DeleteMapping("/removeById/{id}")
    @Parameter(name = "id", description = "角色id")
    @Operation(summary = "删除角色信息", description = "删除角色信息")
    public Result<String> removeById(@NotNull @PathVariable Long id) {
        sysRoleService.removeById(id);
        return Result.success();
    }

    @GetMapping("/findRoleIdsByUserId/{userId}")
    @Parameter(name = "userId", description = "用户 ID")
    @Operation(summary = "根据用户 id查询角色id列表", description = "根据用户 id查询角色id列表")
    public Result<List<String>> findRoleIdsByUserId(@NotNull @PathVariable Long userId) {
        List<String> roleIds = sysRoleService.getRoleIdsByUserId(userId);

        return Result.success(roleIds);
    }

    @GetMapping("/findRoles")
    @Operation(summary = "根据条件查询所有角色列表", description = "根据条件查询所有角色列表")
    public Result<List<FindRoleResponse>> findRoles(@Valid FindRoleRequest request) {
        List<FindRoleResponse> roles = sysRoleService.findRoles(request);
        return Result.success(roles);
    }

    @PutMapping("/updateRolePermissions")
    @Operation(summary = "更新角色权限", description = "更新角色权限")
    Result<String> updateRolePermissions(@Valid @RequestBody UpdateRolePermissionsRequest request) {
        sysRoleService.updateRolePermissions(request);
        return Result.success();
    }

}
