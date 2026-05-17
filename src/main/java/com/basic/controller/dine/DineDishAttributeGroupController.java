package com.basic.controller.dine;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.DineDishAttributeGroup;
import com.basic.domain.request.DineDishAttributeGroupPageRequest;
import com.basic.domain.request.DineDishAttributeGroupRequest;
import com.basic.domain.response.FindDineDishAttributeGroupResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.DineDishAttributeGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/order/dish/attribute/group")
@Tag(name = "点单-菜品属性组 api 接口", description = "菜品属性组相关接口")
public class DineDishAttributeGroupController {

    private final DineDishAttributeGroupService dineDishAttributeGroupService;

    @GetMapping("/all")
    @Operation(summary = "查询所有菜品属性组", description = "查询所有菜品属性组")
    public Result<List<FindDineDishAttributeGroupResponse>> listAll() {
        List<FindDineDishAttributeGroupResponse> responses = dineDishAttributeGroupService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询菜品属性组", description = "分页查询菜品属性组")
    public Result<PageResult<FindDineDishAttributeGroupResponse>> pageQuery(@Valid DineDishAttributeGroupPageRequest request) {
        PageResult<FindDineDishAttributeGroupResponse> responses = dineDishAttributeGroupService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询菜品属性组", description = "根据ID查询菜品属性组")
    public Result<FindDineDishAttributeGroupResponse> getById(@PathVariable @NotNull Long id) {
        DineDishAttributeGroup dineDishAttributeGroup = dineDishAttributeGroupService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品属性组不存在，ID：" + id));
        FindDineDishAttributeGroupResponse response = new FindDineDishAttributeGroupResponse();
        BeanUtils.copyProperties(dineDishAttributeGroup, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建菜品属性组", description = "创建菜品属性组")
    public Result<FindDineDishAttributeGroupResponse> create(@Valid @RequestBody DineDishAttributeGroupRequest request) {
        FindDineDishAttributeGroupResponse response = dineDishAttributeGroupService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新菜品属性组", description = "更新菜品属性组")
    public Result<FindDineDishAttributeGroupResponse> update(@PathVariable @NotNull Long id,
                                                             @RequestBody @Valid DineDishAttributeGroupRequest request) {
        FindDineDishAttributeGroupResponse response = dineDishAttributeGroupService.update(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除菜品属性组", description = "删除菜品属性组")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        dineDishAttributeGroupService.delete(id);
        return Result.success();
    }
}