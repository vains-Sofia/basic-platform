package com.basic.controller.dine;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.DineAttributeGroup;
import com.basic.domain.request.DineAttributeGroupPageRequest;
import com.basic.domain.request.DineAttributeGroupRequest;
import com.basic.domain.response.FindDineAttributeGroupResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.DineAttributeGroupService;
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
@RequestMapping("/order/attribute/group")
@Tag(name = "点单-属性组", description = "属性组相关 api 接口")
public class DineAttributeGroupController {

    private final DineAttributeGroupService dineAttributeGroupService;

    @GetMapping("/all")
    @Operation(summary = "查询所有属性组", description = "查询所有属性组")
    public Result<List<FindDineAttributeGroupResponse>> listAll() {
        List<FindDineAttributeGroupResponse> responses = dineAttributeGroupService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询属性组", description = "分页查询属性组")
    public Result<PageResult<FindDineAttributeGroupResponse>> pageQuery(@Valid DineAttributeGroupPageRequest request) {
        PageResult<FindDineAttributeGroupResponse> responses = dineAttributeGroupService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询属性组", description = "根据ID查询属性组")
    public Result<FindDineAttributeGroupResponse> getById(@PathVariable @NotNull Long id) {
        DineAttributeGroup dineAttributeGroup = dineAttributeGroupService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("属性组不存在，ID：" + id));
        FindDineAttributeGroupResponse response = new FindDineAttributeGroupResponse();
        BeanUtils.copyProperties(dineAttributeGroup, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建属性组", description = "创建属性组")
    public Result<FindDineAttributeGroupResponse> create(@Valid @RequestBody DineAttributeGroupRequest request) {
        FindDineAttributeGroupResponse response = dineAttributeGroupService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新属性组", description = "更新属性组")
    public Result<FindDineAttributeGroupResponse> update(@PathVariable @NotNull Long id,
                                                         @RequestBody @Valid DineAttributeGroupRequest request) {
        FindDineAttributeGroupResponse response = dineAttributeGroupService.update(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除属性组", description = "删除属性组")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        dineAttributeGroupService.delete(id);
        return Result.success();
    }
}