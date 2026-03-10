package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.basic.domain.entity.SysBasicUser;
import com.basic.domain.entity.SysPermission;
import com.basic.domain.entity.SysRolePermission;
import com.basic.domain.entity.SysUserRole;
import com.basic.domain.model.BasicUserDetails;
import com.basic.enums.PermissionTypeEnum;
import com.basic.service.SysBasicUserService;
import com.basic.service.SysPermissionService;
import com.basic.service.SysRolePermissionService;
import com.basic.service.SysUserRoleService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Security 登录时获取用户信息 service 实现
 *
 * @author vains
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserRoleService sysUserRoleService;

    private final SysBasicUserService sysBasicUserService;

    private final SysPermissionService sysPermissionService;

    private final SysRolePermissionService sysRolePermissionService;

    @Nonnull
    @Override
    public UserDetails loadUserByUsername(@Nonnull String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysBasicUser> wrapper = Wrappers.lambdaQuery(SysBasicUser.class)
                .eq(SysBasicUser::getUsername, username)
                .or()
                .eq(SysBasicUser::getNickname, username)
                .or()
                .eq(SysBasicUser::getEmail, username);

        List<SysBasicUser> basicUsers = sysBasicUserService.list(wrapper);
        if (ObjectUtils.isEmpty(basicUsers)) {
            throw UsernameNotFoundException.fromUsername(username);
        }

        // 生成用户详情
        SysBasicUser sysBasicUser = basicUsers.getFirst();
        BasicUserDetails userDetails = new BasicUserDetails();
        BeanUtils.copyProperties(sysBasicUser, userDetails);

        // 查询用户角色 ID
        LambdaQueryWrapper<SysUserRole> userRoleWrapper = Wrappers.lambdaQuery(SysUserRole.class)
                .eq(SysUserRole::getUserId, sysBasicUser.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleService.list(userRoleWrapper);
        if (ObjectUtils.isEmpty(sysUserRoles)) {
            return userDetails;
        }

        // 角色 ID
        Set<Long> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toSet());

        // 查询角色对应的权限 ID
        LambdaQueryWrapper<SysRolePermission> rolePermissionWrapper = Wrappers.lambdaQuery(SysRolePermission.class)
                .in(SysRolePermission::getRoleId, roleIds);
        List<SysRolePermission> rolePermissions = sysRolePermissionService.list(rolePermissionWrapper);
        if (ObjectUtils.isEmpty(rolePermissions)) {
            return userDetails;
        }

        // 权限 ID
        Set<Long> permissionIds = rolePermissions.stream()
                .map(SysRolePermission::getPermissionId)
                .collect(Collectors.toSet());

        // 查询权限
        List<SysPermission> sysPermissions = sysPermissionService.listByIds(permissionIds);

        // 组装权限
        Set<SimpleGrantedAuthority> authorities = sysPermissions.stream()
                .filter(e -> Objects.equals(e.getPermissionType(), PermissionTypeEnum.REST))
                .map(SysPermission::getPermission)
                .filter(e -> !ObjectUtils.isEmpty(e))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
