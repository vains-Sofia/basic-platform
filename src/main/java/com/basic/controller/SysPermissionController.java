package com.basic.controller;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.model.DynamicRouter;
import com.basic.domain.request.FindPermissionPageRequest;
import com.basic.domain.request.FindPermissionRequest;
import com.basic.domain.request.SavePermissionRequest;
import com.basic.domain.response.FindPermissionResponse;
import com.basic.service.SysPermissionService;
import com.basic.validation.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RBAC 权限相关接口
 *
 * @author YuJx
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
@Tag(name = "RBAC 权限相关接口", description = "RBAC 权限相关接口")
public class SysPermissionController {

    private final SysPermissionService sysPermissionService;

    @GetMapping("/findByPage")
    @Operation(summary = "分页查询权限信息列表", description = "分页查询权限信息列表")
    public Result<PageResult<FindPermissionResponse>> findByPage(@Valid FindPermissionPageRequest request) {
        PageResult<FindPermissionResponse> pageResult = sysPermissionService.findByPage(request);
        return Result.success(pageResult);
    }

    @GetMapping("/findPermissions")
    @Operation(summary = "查询权限信息列表", description = "查询权限信息列表")
    public Result<List<FindPermissionResponse>> findPermissions(@Valid FindPermissionRequest request) {
        List<FindPermissionResponse> permissions = sysPermissionService.findPermissions(request);
        return Result.success(permissions);
    }

    @GetMapping("/permissionDetails/{id}")
    @Parameter(name = "id", description = "权限 ID")
    @Operation(summary = "查询权限详情", description = "根据权限 ID查询权限详情")
    public Result<FindPermissionResponse> permissionDetails(@NotNull @PathVariable Long id) {
        FindPermissionResponse permissionResponse = sysPermissionService.permissionDetails(id);
        return Result.success(permissionResponse);
    }

    @PostMapping("/insertPermission")
    @Operation(summary = "添加权限信息", description = "添加权限信息")
    public Result<String> insertPermission(@Validated @RequestBody SavePermissionRequest request) {
        // 置空id，防止插入变修改
        request.setId(null);
        sysPermissionService.savePermission(request);
        return Result.success();
    }

    @PutMapping("/updatePermission")
    @Operation(summary = "修改权限信息", description = "修改权限信息")
    public Result<String> updatePermission(@Validated(Update.class) @RequestBody SavePermissionRequest request) {
        sysPermissionService.savePermission(request);
        return Result.success();
    }

    @PutMapping("/batchUpdatePermissions")
    @Operation(summary = "批量修改权限信息", description = "批量修改权限信息")
    public Result<String> batchUpdatePermissions(@Validated(Update.class) @RequestBody List<SavePermissionRequest> requests) {
        sysPermissionService.batchUpdatePermissions(requests);
        return Result.success();
    }

    @DeleteMapping("/removeById/{id}")
    @Parameter(name = "id", description = "权限 id")
    @Operation(summary = "删除权限信息", description = "删除权限信息")
    public Result<String> removeById(@NotNull @PathVariable Long id) {
        sysPermissionService.removeById(id);
        return Result.success();
    }

    @GetMapping("/findPermissionIdsByRoleId/{roleId}")
    @Operation(summary = "根据角色 id查询权限id列表", description = "根据角色 id查询权限id列表")
    public Result<List<String>> findPermissionIdsByRoleId(@NotNull @PathVariable Long roleId) {
        List<String> permissionIds = sysPermissionService.findPermissionIdsByRoleId(roleId);
        return Result.success(permissionIds);
    }

    @PostMapping("/findNonParentPermissions")
    @Operation(summary = "根据 权限id 列表获取没有父节点的权限id列表", description = "根据 权限id 列表获取没有父节点的权限id列表")
    public Result<List<String>> findNonParentPermissions(@NotEmpty @RequestBody List<@NotNull Long> permissionIds) {
        List<String> nonChildrenList = sysPermissionService.findNonParentPermissions(permissionIds);
        return Result.success(nonChildrenList);
    }

    @GetMapping("/findUserRouters")
    @Operation(summary = "获取当前用户的菜单列表", description = "获取当前用户的菜单列表")
    public Result<List<DynamicRouter>> findUserRouters() {
        List<DynamicRouter> dynamicRouters = sysPermissionService.findUserRouters();
        return Result.success(dynamicRouters);
    }

}
