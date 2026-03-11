package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysBasicUser;
import com.basic.domain.request.FindBasicUserPageRequest;
import com.basic.domain.request.SaveBasicUserRequest;
import com.basic.domain.request.UpdateUserRolesRequest;
import com.basic.domain.response.AuthenticatedUserResponse;
import com.basic.domain.response.FindBasicUserResponse;
import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;

/**
 * 针对表【sys_basic_user(基础用户信息表)】的数据库操作Service
 *
 * @author vains
 */
public interface SysBasicUserService extends IService<SysBasicUser> {

    /**
     * 分页查询基础用户信息列表
     *
     * @param request 分页查询基础用户信息列表入参
     * @return 用户信息
     */
    PageResult<FindBasicUserResponse> findByPage(@Valid FindBasicUserPageRequest request);

    /**
     * 添加/修改用户信息(如果id不为空则是修改，否则是添加)
     *
     * @param request 用户信息
     */
    void saveBasicUser(SaveBasicUserRequest request);

    /**
     * 获取登录用户信息
     *
     * @return 登录用户信息
     */
    AuthenticatedUserResponse getLoginUserinfo();

    /**
     * 更新用户角色
     *
     * @param request 更新用户角色入参
     */
    void updateUserRoles(@Valid UpdateUserRolesRequest request);

    /**
     * 根据用户 ID列表获取用户列表
     *
     * @param ids 用户主键 ID列表
     * @return 用户列表
     */
    List<FindBasicUserResponse> getByIds(Collection<Long> ids);
}
