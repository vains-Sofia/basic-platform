package com.basic.controller;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.SysBasicUser;
import com.basic.domain.request.FindBasicUserPageRequest;
import com.basic.domain.request.SaveBasicUserRequest;
import com.basic.domain.request.UpdateUserRolesRequest;
import com.basic.domain.response.AuthenticatedUserResponse;
import com.basic.domain.response.FindBasicUserResponse;
import com.basic.service.SysBasicUserService;
import com.basic.validation.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 基础用户信息接口
 *
 * @author vains
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "基础用户信息接口", description = "基础用户信息接口")
public class SysBasicUserController {

    private final SysBasicUserService basicUserService;

    @GetMapping("/findByPage")
    @Operation(summary = "分页查询基础用户信息列表", description = "分页查询基础用户信息列表")
    public Result<PageResult<FindBasicUserResponse>> findByPage(@Valid FindBasicUserPageRequest request) {
        PageResult<FindBasicUserResponse> pageResult = basicUserService.findByPage(request);
        return Result.success(pageResult);
    }

    @GetMapping("/userDetails/{id}")
    @Parameter(name = "id", description = "用户 ID")
    @Operation(summary = "查询用户详情", description = "根据用户 id 查询用户详情")
    public Result<FindBasicUserResponse> userDetails(@Valid @NotNull @PathVariable Long id) {
        Optional<SysBasicUser> opt = basicUserService.getOptById(id);
        FindBasicUserResponse userResponse = opt.map(u -> {
            FindBasicUserResponse basicUserResponse = new FindBasicUserResponse();
            BeanUtils.copyProperties(u, basicUserResponse);
            return basicUserResponse;
        }).orElse(null);
        return Result.success(userResponse);
    }

    @PostMapping("/insertBasicUser")
    @Operation(summary = "添加一条用户信息", description = "添加一条用户信息")
    public Result<String> insertBasicUser(@Validated @RequestBody SaveBasicUserRequest request) {
        // 置空id，防止插入变修改
        request.setId(null);
        basicUserService.saveBasicUser(request);
        return Result.success();
    }

    @PutMapping("/updateBasicUser")
    @Operation(summary = "修改用户信息", description = "修改用户信息")
    public Result<String> updateBasicUser(@Validated(Update.class) @RequestBody SaveBasicUserRequest request) {
        basicUserService.saveBasicUser(request);
        return Result.success();
    }

    @DeleteMapping("/removeById/{id}")
    @Parameter(name = "id", description = "用户 id")
    @Operation(summary = "删除用户信息", description = "删除用户信息")
    public Result<String> removeById(@Valid @NotNull @PathVariable Long id) {
        basicUserService.removeById(id);
        return Result.success();
    }

    @GetMapping("/loginUserinfo")
    @Operation(summary = "获取登录用户信息", description = "获取登录用户信息")
    public Result<AuthenticatedUserResponse> loginUserinfo() {
        AuthenticatedUserResponse userInfoResponse = basicUserService.getLoginUserinfo();
        return Result.success(userInfoResponse);
    }

    @PutMapping("/updateUserRoles")
    @Operation(summary = "更新用户角色", description = "更新用户角色")
    public Result<String> updateUserRoles(@Valid @RequestBody UpdateUserRolesRequest request) {
        basicUserService.updateUserRoles(request);
        return Result.success();
    }

    @PostMapping("/getByIds")
    @Operation(summary = "根据用户 ID列表获取用户列表", description = "根据用户 ID列表获取用户列表")
    public Result<List<FindBasicUserResponse>> getByIds(@NotEmpty @RequestBody Collection<Long> ids) {
        List<FindBasicUserResponse> users = basicUserService.getByIds(ids);
        return Result.success(users);
    }

}
