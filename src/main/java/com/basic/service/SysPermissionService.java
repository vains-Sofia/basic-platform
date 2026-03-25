package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysPermission;
import com.basic.domain.model.DynamicRouter;
import com.basic.domain.request.FindPermissionPageRequest;
import com.basic.domain.request.FindPermissionRequest;
import com.basic.domain.request.SavePermissionRequest;
import com.basic.domain.response.FindPermissionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【sys_permission(RBAC权限表)】的数据库操作Service
 *
 * @author vains
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 分页查询权限信息列表
     *
     * @param request 分页查询权限信息列表入参
     * @return 权限信息
     */
    PageResult<FindPermissionResponse> findByPage(@Valid FindPermissionPageRequest request);

    /**
     * 查询权限信息列表
     *
     * @param request 查询权限信息列表入参
     * @return 权限信息
     */
    List<FindPermissionResponse> findPermissions(@Valid FindPermissionRequest request);

    /**
     * 查询权限详情
     *
     * @param id 权限 ID
     * @return 权限信息
     */
    FindPermissionResponse permissionDetails(@NotNull Long id);

    /**
     * 添加或修改权限信息
     *
     * @param request 权限信息
     */
    void savePermission(SavePermissionRequest request);

    /**
     * 批量修改权限信息
     *
     * @param requests 权限信息列表
     */
    void batchUpdatePermissions(List<SavePermissionRequest> requests);

    /**
     * 根据 角色id 查询权限id列表
     *
     * @param roleId 角色 ID
     * @return 权限id 列表
     */
    List<String> findPermissionIdsByRoleId(@NotNull Long roleId);

    /**
     * 根据 权限id 列表查询非父权限的权限id列表
     *
     * @param permissionIds 权限id 列表
     * @return 非子权限的 权限id列表
     */
    List<String> findNonParentPermissions(@NotEmpty List<@NotNull Long> permissionIds);

    /**
     * 检索当前用户的动态菜单路由器列表。
     *
     * @return 包装 DynamicRouter 对象列表的结果，表示用户的菜单结构
     */
    List<DynamicRouter> findUserRouters();

    /**
     * 根据 用户id 查询权限列表
     *
     * @param userId 角色 ID
     * @return 权限列表
     */
    List<SysPermission> findPermissionsByUserId(@NotNull Long userId);

    /**
     * 根据 权限ID 删除权限，并删除所有子级
     *
     * @param id 主键 ID
     */
    void removeWithChildren(@NotNull Long id);
}
