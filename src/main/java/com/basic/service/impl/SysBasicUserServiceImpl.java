package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysBasicUser;
import com.basic.domain.entity.SysUserRole;
import com.basic.domain.model.BasicUserDetails;
import com.basic.domain.request.FindBasicUserPageRequest;
import com.basic.domain.request.SaveBasicUserRequest;
import com.basic.domain.request.UpdateUserRolesRequest;
import com.basic.domain.response.AuthenticatedUserResponse;
import com.basic.domain.response.FindBasicUserResponse;
import com.basic.enums.OAuth2AccountPlatformEnum;
import com.basic.exception.CloudIllegalArgumentException;
import com.basic.mapper.SysBasicUserMapper;
import com.basic.mapper.SysUserRoleMapper;
import com.basic.service.SysBasicUserService;
import com.basic.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 针对表【sys_basic_user(基础用户信息表)】的数据库操作Service实现
 *
 * @author vains
 */
@Service
@RequiredArgsConstructor
public class SysBasicUserServiceImpl extends ServiceImpl<SysBasicUserMapper, SysBasicUser>
        implements SysBasicUserService {

    private final PasswordEncoder passwordEncoder;

    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public PageResult<FindBasicUserResponse> findByPage(FindBasicUserPageRequest request) {
        // 条件构造器
        LambdaQueryWrapper<SysBasicUser> wrapper = Wrappers.lambdaQuery(SysBasicUser.class)
                .like(!ObjectUtils.isEmpty(request.getNickname()), SysBasicUser::getNickname,
                        request.getNickname())
                .like(!ObjectUtils.isEmpty(request.getEmail()), SysBasicUser::getEmail,
                        request.getEmail())
                .eq(!ObjectUtils.isEmpty(request.getGender()), SysBasicUser::getGender,
                        request.getGender())
                .orderByDesc(SysBasicUser::getCreateTime);

        Page<SysBasicUser> objectPage = Page.of(request.getCurrent(), request.getSize());

        Page<SysBasicUser> basicUserPage = this.page(objectPage, wrapper);

        // 转为响应 bean
        IPage<FindBasicUserResponse> converted = basicUserPage.convert(e -> {
            FindBasicUserResponse basicUserResponse = new FindBasicUserResponse();
            BeanUtils.copyProperties(e, basicUserResponse);
            return basicUserResponse;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public void saveBasicUser(SaveBasicUserRequest request) {
        boolean hasId = request.getId() != null;
        // 检查邮箱是否已被绑定
        LambdaQueryWrapper<SysBasicUser> wrapper = Wrappers.lambdaQuery(SysBasicUser.class)
                .eq(SysBasicUser::getEmail, request.getEmail())
                .ne(hasId, SysBasicUser::getId, request.getId());
        boolean exists = this.exists(wrapper);
        if (exists) {
            throw new CloudIllegalArgumentException("邮箱已被注册。");
        }

        // 组装用户信息
        SysBasicUser sysBasicUser = new SysBasicUser();
        BeanUtils.copyProperties(request, sysBasicUser);

        // 插入时初始化 id与密码
        if (!hasId) {
            // 密码加密
            if (ObjectUtils.isEmpty(request.getPassword())) {
                // 无密码
                sysBasicUser.setPassword("{noop}");
            } else {
                sysBasicUser.setPassword(passwordEncoder.encode(request.getPassword()));
            }
        } else {
            // 设置插入相关的审计信息
            Optional<SysBasicUser> basicUserOptional = this.getOptById(request.getId());
            if (basicUserOptional.isPresent()) {
                SysBasicUser existsBasicUser = basicUserOptional.get();
                // 不修改
                if (ObjectUtils.isEmpty(request.getPicture())) {
                    sysBasicUser.setPicture(existsBasicUser.getPicture());
                }
                sysBasicUser.setDeleted(existsBasicUser.getDeleted());
                sysBasicUser.setPassword(existsBasicUser.getPassword());
                sysBasicUser.setCreateBy(existsBasicUser.getCreateBy());
                sysBasicUser.setCreateName(existsBasicUser.getCreateName());
                sysBasicUser.setCreateTime(existsBasicUser.getCreateTime());
                sysBasicUser.setAccountPlatform(existsBasicUser.getAccountPlatform());
                // 默认邮箱正确
                sysBasicUser.setEmailVerified(!ObjectUtils.isEmpty(existsBasicUser.getEmail()));
                // 默认手机号码正确
                sysBasicUser.setPhoneNumberVerified(!ObjectUtils.isEmpty(existsBasicUser.getPhoneNumber()));
            }
        }

        // 初始化默认信息
        sysBasicUser.setDeleted(0);
        sysBasicUser.setEmailVerified(Boolean.FALSE);
        if (sysBasicUser.getAccountPlatform() == null) {
            sysBasicUser.setAccountPlatform(OAuth2AccountPlatformEnum.SYSTEM);
        }

        this.saveOrUpdate(sysBasicUser);
    }

    @Override
    public AuthenticatedUserResponse getLoginUserinfo() {
        AuthenticatedUserResponse userResponse = new AuthenticatedUserResponse();
        BasicUserDetails loginUser = SecurityUtils.getLoginUser();

        if (ObjectUtils.isEmpty(loginUser)) {
            return userResponse;
        }

        BeanUtils.copyProperties(loginUser, userResponse);

        // 暂时不返回权限
        userResponse.setAuthorities(null);
        return userResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRoles(UpdateUserRolesRequest request) {
        SysBasicUser basicUser = this.getById(request.getUserId());
        if (basicUser == null) {
            throw new CloudIllegalArgumentException("用户不存在.");
        }
        // 删除用户原有的角色
        LambdaQueryWrapper<SysUserRole> deleteWrapper = Wrappers.lambdaQuery(SysUserRole.class)
                .eq(SysUserRole::getUserId, basicUser.getId());
        sysUserRoleMapper.delete(deleteWrapper);
        // 转为用户角色关联实体
        List<SysUserRole> userRoles = request.getRoleIds().stream().map(id -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(request.getUserId());
            userRole.setRoleId(id);
            return userRole;
        }).toList();

        if (ObjectUtils.isEmpty(userRoles)) {
            return;
        }
        sysUserRoleMapper.insert(userRoles);
    }

    @Override
    public List<FindBasicUserResponse> getByIds(Collection<Long> ids) {
        List<SysBasicUser> sysBasicUsers = this.listByIds(ids);
        return sysBasicUsers.stream()
                .map(u -> {
                    FindBasicUserResponse basicUserResponse = new FindBasicUserResponse();
                    BeanUtils.copyProperties(u, basicUserResponse);
                    return basicUserResponse;
                }).toList();
    }
}




