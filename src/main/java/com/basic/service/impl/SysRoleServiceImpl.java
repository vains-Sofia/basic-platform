package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysRole;
import com.basic.domain.entity.SysRolePermission;
import com.basic.domain.entity.SysUserRole;
import com.basic.domain.request.FindRolePageRequest;
import com.basic.domain.request.FindRoleRequest;
import com.basic.domain.request.SaveRoleRequest;
import com.basic.domain.request.UpdateRolePermissionsRequest;
import com.basic.domain.response.FindRoleResponse;
import com.basic.exception.CloudIllegalArgumentException;
import com.basic.mapper.SysRoleMapper;
import com.basic.mapper.SysRolePermissionMapper;
import com.basic.mapper.SysUserRoleMapper;
import com.basic.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * 针对表【sys_role(RBAC角色表)】的数据库操作Service实现
 *
 * @author vains
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    private final SysUserRoleMapper sysUserRoleMapper;

    private final SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public PageResult<FindRoleResponse> findByPage(FindRolePageRequest request) {
        LambdaQueryWrapper<SysRole> wrapper = Wrappers.lambdaQuery(SysRole.class)
                .like(!ObjectUtils.isEmpty(request.getCode()), SysRole::getCode, request.getCode())
                .like(!ObjectUtils.isEmpty(request.getName()), SysRole::getName, request.getName())
                .like(!ObjectUtils.isEmpty(request.getDescription()), SysRole::getDescription, request.getDescription())
                .orderByDesc(SysRole::getCreateTime);

        Page<SysRole> objectPage = Page.of(request.getCurrent(), request.getSize());

        Page<SysRole> rolePage = this.page(objectPage, wrapper);

        // 转为响应 bean
        IPage<FindRoleResponse> converted = rolePage.convert(e -> {
            FindRoleResponse response = new FindRoleResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindRoleResponse roleDetails(Long id) {
        return this.getOptById(id).map(u -> {
            FindRoleResponse roleResponse = new FindRoleResponse();
            BeanUtils.copyProperties(u, roleResponse);
            return roleResponse;
        }).orElse(null);
    }

    @Override
    public void saveRole(SaveRoleRequest request) {
        // id是否为空，不为空是修改
        boolean hasId = request.getId() != null;
        // 检查角色代码是否已存在
        LambdaQueryWrapper<SysRole> wrapper = Wrappers.lambdaQuery(SysRole.class)
                // 角色代码
                .eq(SysRole::getCode, request.getCode())
                // 修改需排除当前数据
                .ne(hasId, SysRole::getId, request.getId());
        boolean exists = this.exists(wrapper);
        if (exists) {
            throw new CloudIllegalArgumentException("角色代码已存在。");
        }
        // 组装实体信息
        SysRole role = new SysRole();

        // 插入时初始化
        if (!hasId) {
            BeanUtils.copyProperties(request, role);
            // 初始化默认信息
            role.setDeleted(0);
        } else {
            // 设置插入相关的审计信息
            Optional<SysRole> roleOptional = this.getOptById(request.getId());
            if (roleOptional.isPresent()) {
                // 转移数据至实体
                SysRole existsRole = roleOptional.get();
                role.setId(existsRole.getId());
                role.setCode(request.getCode());
                role.setName(request.getName());
                role.setDescription(request.getDescription());
                role.setDeleted(existsRole.getDeleted());
                role.setCreateBy(existsRole.getCreateBy());
                role.setCreateName(existsRole.getCreateName());
                role.setCreateTime(existsRole.getCreateTime());
            }
        }

        this.saveOrUpdate(role);
    }

    @Override
    public List<String> getRoleIdsByUserId(Long userId) {
        LambdaQueryWrapper<SysUserRole> wrapper = Wrappers.lambdaQuery(SysUserRole.class)
                .eq(SysUserRole::getUserId, userId);
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(wrapper);

        return userRoles.stream().map(SysUserRole::getRoleId).map(String::valueOf).toList();
    }

    @Override
    public List<FindRoleResponse> findRoles(FindRoleRequest request) {
        // 条件构造器
        LambdaQueryWrapper<SysRole> wrapper = Wrappers.lambdaQuery(SysRole.class)
                .like(!ObjectUtils.isEmpty(request.getName()), SysRole::getName, request.getName())
                .like(!ObjectUtils.isEmpty(request.getCode()), SysRole::getCode, request.getCode())
                .like(!ObjectUtils.isEmpty(request.getDescription()), SysRole::getDescription, request.getDescription());

        // 执行查询
        List<SysRole> findResult = this.list(wrapper);
        // 转为响应 bean
        return findResult.stream()
                .map(e -> {
                    FindRoleResponse roleResponse = new FindRoleResponse();
                    BeanUtils.copyProperties(e, roleResponse);
                    return roleResponse;
                })
                .toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRolePermissions(UpdateRolePermissionsRequest request) {
        SysRole sysRole = this.getById(request.getRoleId());
        if (sysRole == null) {
            throw new CloudIllegalArgumentException("角色不存在.");
        }
        LambdaUpdateWrapper<SysRolePermission> wrapper = Wrappers.lambdaUpdate(SysRolePermission.class)
                .eq(SysRolePermission::getRoleId, sysRole.getId());
        // 删除原有权限
        sysRolePermissionMapper.delete(wrapper);

        // 插入新权限
        List<SysRolePermission> rolePermissions = request.getPermissionIds().stream().map(id -> {
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRoleId(request.getRoleId());
            rolePermission.setPermissionId(id);
            return rolePermission;
        }).toList();
        if (ObjectUtils.isEmpty(rolePermissions)) {
            return;
        }
        sysRolePermissionMapper.insert(rolePermissions);
    }
}




