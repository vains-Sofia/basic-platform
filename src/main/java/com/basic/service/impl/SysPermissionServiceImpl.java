package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.converter.RouterConverter;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysPermission;
import com.basic.domain.entity.SysRolePermission;
import com.basic.domain.entity.SysUserRole;
import com.basic.domain.model.DynamicRouter;
import com.basic.domain.request.FindPermissionPageRequest;
import com.basic.domain.request.FindPermissionRequest;
import com.basic.domain.request.SavePermissionRequest;
import com.basic.domain.response.FindPermissionResponse;
import com.basic.enums.PermissionTypeEnum;
import com.basic.exception.CloudIllegalArgumentException;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.SysPermissionMapper;
import com.basic.mapper.SysRolePermissionMapper;
import com.basic.mapper.SysUserRoleMapper;
import com.basic.service.SysPermissionService;
import com.basic.util.SecurityUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 针对表【sys_permission(RBAC权限表)】的数据库操作Service实现
 *
 * @author vains
 */
@Service
@RequiredArgsConstructor
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
        implements SysPermissionService {

    private final SysUserRoleMapper sysUserRoleMapper;

    private final SysRolePermissionMapper sysRolePermissionMapper;

    private final RouterConverter routerConverter = new RouterConverter();

    @Override
    public PageResult<FindPermissionResponse> findByPage(FindPermissionPageRequest request) {
        // 条件构造器
        LambdaQueryWrapper<SysPermission> wrapper = Wrappers.lambdaQuery(SysPermission.class)
                .like(!ObjectUtils.isEmpty(request.getName()), SysPermission::getName, request.getName())
                .like(!ObjectUtils.isEmpty(request.getPermission()), SysPermission::getPermission,
                        request.getPermission())
                .like(!ObjectUtils.isEmpty(request.getPath()), SysPermission::getPath, request.getPath())
                .eq(!ObjectUtils.isEmpty(request.getPermissionType()), SysPermission::getPermissionType,
                        request.getPermissionType())
                .orderByAsc(SysPermission::getSortOrder);

        Page<SysPermission> objectPage = Page.of(request.getCurrent(), request.getSize());

        Page<SysPermission> permissionPage = this.page(objectPage, wrapper);

        // 转为响应 bean
        IPage<FindPermissionResponse> converted = permissionPage.convert(e -> {
            FindPermissionResponse permissionResponse = new FindPermissionResponse();
            BeanUtils.copyProperties(e, permissionResponse);
            return permissionResponse;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public List<FindPermissionResponse> findPermissions(FindPermissionRequest request) {
        // 条件构造器
        LambdaQueryWrapper<SysPermission> wrapper = Wrappers.lambdaQuery(SysPermission.class)
                .like(!ObjectUtils.isEmpty(request.getPath()), SysPermission::getPath, request.getPath())
                .like(!ObjectUtils.isEmpty(request.getName()), SysPermission::getName, request.getName())
                .like(!ObjectUtils.isEmpty(request.getPermission()), SysPermission::getPermission, request.getPermission())
                .eq(!ObjectUtils.isEmpty(request.getPermissionType()), SysPermission::getPermissionType, request.getPermissionType())
                .orderByAsc(SysPermission::getSortOrder);

        List<SysPermission> permissions = this.list(wrapper);
        return permissions.stream()
                .map(e -> {
                    FindPermissionResponse permissionResponse = new FindPermissionResponse();
                    BeanUtils.copyProperties(e, permissionResponse);
                    return permissionResponse;
                }).toList();
    }

    @Override
    public FindPermissionResponse permissionDetails(Long id) {
        return this.getOptById(id)
                .map(e -> {
                    FindPermissionResponse permissionResponse = new FindPermissionResponse();
                    BeanUtils.copyProperties(e, permissionResponse);
                    return permissionResponse;
                }).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePermission(SavePermissionRequest request) {
        // id是否为空，不为空是修改
        boolean hasId = request.getId() != null;
        // 检查路径是否已存在
        LambdaQueryWrapper<SysPermission> wrapper = Wrappers.lambdaQuery(SysPermission.class)
                // 请求路径
                .eq(SysPermission::getPath, request.getPath());

        // 请求方式
        if (ObjectUtils.isEmpty(request.getRequestMethod())) {
            wrapper.isNull(SysPermission::getRequestMethod);
        } else {
            wrapper.eq(SysPermission::getRequestMethod, request.getRequestMethod());
        }

        // 修改需排除当前数据
        wrapper.ne(hasId, SysPermission::getId, request.getId());
        boolean exists = this.exists(wrapper);
        if (exists) {
            throw new CloudIllegalArgumentException("路径已存在。");
        }

        // 组装实体信息
        SysPermission permission = new SysPermission();
        BeanUtils.copyProperties(request, permission);

        // 插入时初始化
        if (!hasId) {
            // 初始化默认信息
            permission.setDeleted(0);
        } else {
            // 设置插入相关的审计信息
            Optional<SysPermission> permissionOptional = this.getOptById(request.getId());
            if (permissionOptional.isPresent()) {
                SysPermission existsPermission = permissionOptional.get();
                permission.setCreateBy(existsPermission.getCreateBy());
                permission.setCreateName(existsPermission.getCreateName());
                permission.setCreateTime(existsPermission.getCreateTime());
            }
        }

        // 默认设置不删除
        if (permission.getDeleted() == null) {
            permission.setDeleted(0);
        }
        this.saveOrUpdate(permission);

        // 刷新权限缓存
//        this.refreshPermissionCache();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdatePermissions(List<SavePermissionRequest> requests) {
        // 判断入参是否存在重复
        Set<String> duplicateChecker = new HashSet<>();
        List<String> duplicates = new ArrayList<>();

        for (SavePermissionRequest request : requests) {
            String key = buildUniqueKey(request.getPath(), request.getRequestMethod());
            if (!duplicateChecker.add(key)) {
                duplicates.add(key);
            }
        }

        if (!duplicates.isEmpty()) {
            throw new CloudServiceException("路径和请求方法组合不能重复: " + String.join(", ", duplicates));
        }

        // 检查是否和已有权限冲突
        for (SavePermissionRequest request : requests) {
            LambdaQueryWrapper<SysPermission> wrapper = Wrappers.lambdaQuery(SysPermission.class);

            // 请求方式
            if (ObjectUtils.isEmpty(request.getRequestMethod())) {
                wrapper.isNull(SysPermission::getRequestMethod);
            } else {
                wrapper.eq(SysPermission::getRequestMethod, request.getRequestMethod());
            }
            // 请求路径
            wrapper.eq(SysPermission::getPath, request.getPath());
            // 修改需排除当前数据
            wrapper.ne(request.getId() != null, SysPermission::getId, request.getId());
            boolean exists = this.exists(wrapper);
            if (exists) {
                duplicates.add(buildUniqueKey(request.getPath(), request.getRequestMethod()));
            }
        }

        if (!duplicates.isEmpty()) {
            throw new CloudServiceException("路径和请求方法组合已存在: " + String.join(", ", duplicates));
        }

        // 批量更新权限
        List<SysPermission> permissionsToSave = requests.stream().map(request -> {
            SysPermission permission = new SysPermission();
            BeanUtils.copyProperties(request, permission);
            // 如果是新增
            if (request.getId() == null) {
                permission.setDeleted(0);
            } else {
                // 如果是修改，则保留原有的创建信息
                Optional<SysPermission> existingPermission = this.getOptById(request.getId());
                existingPermission.ifPresent(existing -> {
                    permission.setCreateBy(existing.getCreateBy());
                    permission.setCreateName(existing.getCreateName());
                    permission.setCreateTime(existing.getCreateTime());
                });
            }
            // 默认设置不删除
            if (permission.getDeleted() == null) {
                permission.setDeleted(0);
            }
            return permission;
        }).toList();

        if (!ObjectUtils.isEmpty(permissionsToSave)) {
            this.saveOrUpdateBatch(permissionsToSave);
        }

        // 刷新权限缓存
//        this.refreshPermissionCache();
    }

    @Override
    public List<String> findPermissionIdsByRoleId(Long roleId) {
        LambdaQueryWrapper<SysRolePermission> wrapper = Wrappers.lambdaQuery(SysRolePermission.class)
                .eq(SysRolePermission::getRoleId, roleId);
        List<SysRolePermission> rolePermissions = sysRolePermissionMapper.selectList(wrapper);
        if (rolePermissions == null || rolePermissions.isEmpty()) {
            return null;
        }
        // 提取所有权限 id
        List<Long> permissionIds = rolePermissions.stream()
                .map(SysRolePermission::getPermissionId)
                .toList();

        // 过滤掉有子节点的权限id(ElementPlus Tree组件如果有设置父节点选中，则不管所有子节点是否选中，父节点都选中，这时会让子节点默认全部选中)
        return this.findNonParentPermissions(permissionIds);
    }

    @Override
    public List<String> findNonParentPermissions(List<@NotNull Long> permissionIds) {
        // 过滤掉有子节点的权限id(ElementPlus Tree组件如果有设置父节点选中，则不管所有子节点是否选中，父节点都选中，这时会让子节点默认全部选中)
        List<SysPermission> permissions = this.listByIds(permissionIds);
        // 提取所有 父节点id
        Set<Long> parentPermissionIds = permissions.stream()
                .map(SysPermission::getParentId)
                .filter(Objects::nonNull)
                // 去掉根节点标识
                .filter(parentId -> parentId != 0)
                .collect(Collectors.toSet());

        // 过滤掉 parentIds 中的节点（也就是有子节点的父节点）
        return permissionIds.stream()
                .filter(parentId -> !parentPermissionIds.contains(parentId))
                .map(String::valueOf)
                .toList();
    }

    @Override
    public List<DynamicRouter> findUserRouters() {
        // 获取当前登录用户信息
        String userId = SecurityUtils.getLoginUserId();
        if (ObjectUtils.isEmpty(userId)) {
            throw new CloudServiceException("获取当前用户信息失败.");
        }

        // 获取用户拥有的权限列表
        List<SysPermission> permissions = this.findPermissionsByUserId(Long.valueOf(userId));

        List<SysPermission> menus = permissions.stream()
                .filter(e -> PermissionTypeEnum.isMenuType(e.getPermissionType()))
                .toList();
        // 转为 DynamicRouter 返回
        return routerConverter.convertToRouterTree(menus);
    }

    @Override
    public List<SysPermission> findPermissionsByUserId(Long userId) {
        // 查询用户角色 ID
        LambdaQueryWrapper<SysUserRole> userRoleWrapper = Wrappers.lambdaQuery(SysUserRole.class)
                .eq(SysUserRole::getUserId, userId);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(userRoleWrapper);
        if (!ObjectUtils.isEmpty(sysUserRoles)) {
            // 角色 ID
            Set<Long> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toSet());

            // 查询角色对应的权限
            LambdaQueryWrapper<SysRolePermission> rolePermissionWrapper = Wrappers.lambdaQuery(SysRolePermission.class)
                    .in(SysRolePermission::getRoleId, roleIds);
            List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectList(rolePermissionWrapper);

            if (!ObjectUtils.isEmpty(sysRolePermissions)) {
                // 权限 ID
                Set<Long> permissionIds = sysRolePermissions.stream().map(SysRolePermission::getPermissionId).collect(Collectors.toSet());
                return this.listByIds(permissionIds);
            }

        }
        return List.of();
    }

    /**
     * 构建唯一键，用于检查重复的路径和请求方法组合
     *
     * @param path          请求路径
     * @param requestMethod 请求方法
     * @return 唯一键
     */
    private String buildUniqueKey(String path, String requestMethod) {
        return (path != null ? path : "") + ":" + (requestMethod != null ? requestMethod : "");
    }

}




