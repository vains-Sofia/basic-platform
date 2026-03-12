package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.basic.domain.entity.SysBasicUser;
import com.basic.domain.entity.SysPermission;
import com.basic.domain.model.BasicUserDetails;
import com.basic.enums.PermissionTypeEnum;
import com.basic.service.SysBasicUserService;
import com.basic.service.SysPermissionService;
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

    private final SysBasicUserService sysBasicUserService;

    private final SysPermissionService sysPermissionService;

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

        // 查询用户权限列表
        List<SysPermission> permissions = sysPermissionService.findPermissionsByUserId(sysBasicUser.getId());

        if (ObjectUtils.isEmpty(permissions)) {
            return userDetails;
        }

        // 组装权限
        Set<SimpleGrantedAuthority> authorities = permissions.stream()
                .filter(e -> Objects.equals(e.getPermissionType(), PermissionTypeEnum.REST))
                .map(SysPermission::getPermission)
                .filter(e -> !ObjectUtils.isEmpty(e))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
