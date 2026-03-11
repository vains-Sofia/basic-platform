package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysRole;
import com.basic.domain.request.FindRolePageRequest;
import com.basic.domain.request.FindRoleRequest;
import com.basic.domain.request.SaveRoleRequest;
import com.basic.domain.request.UpdateRolePermissionsRequest;
import com.basic.domain.response.FindRoleResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【sys_role(RBAC角色表)】的数据库操作Service
 *
 * @author vains
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询角色信息列表
     *
     * @param request 分页查询角色信息列表入参
     * @return 角色信息
     */
    PageResult<FindRoleResponse> findByPage(@Valid FindRolePageRequest request);

    /**
     * 查询角色详情
     *
     * @param id 角色 ID
     * @return 角色详情数据
     */
    FindRoleResponse roleDetails(@NotNull Long id);

    /**
     * 保存/修改角色数据
     *
     * @param request 新的角色信息
     */
    void saveRole(SaveRoleRequest request);

    /**
     * 根据 用户ID 获取对应的角色ID列表
     *
     * @param userId 用户 ID
     * @return 角色 ID列表
     */
    List<String> getRoleIdsByUserId(@NotNull Long userId);

    /**
     * 查询角色信息列表
     *
     * @param request 查询角色信息列表入参
     * @return 角色信息
     */
    List<FindRoleResponse> findRoles(@Valid FindRoleRequest request);

    /**
     * 变更角色权限
     *
     * @param request 变更角色权限入参
     */
    void updateRolePermissions(@Valid UpdateRolePermissionsRequest request);
}
