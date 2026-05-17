package com.basic.controller.dine;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.DineDishAttributeOption;
import com.basic.domain.request.DineDishAttributeOptionPageRequest;
import com.basic.domain.request.DineDishAttributeOptionRequest;
import com.basic.domain.response.FindDineDishAttributeOptionResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.DineDishAttributeOptionService;
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
@RequestMapping("/order/dish/attribute/option")
@Tag(name = "点单-菜品属性选项 api 接口", description = "菜品属性选项相关接口")
public class DineDishAttributeOptionController {

    private final DineDishAttributeOptionService dineDishAttributeOptionService;

    @GetMapping("/all")
    @Operation(summary = "查询所有菜品属性选项", description = "查询所有菜品属性选项")
    public Result<List<FindDineDishAttributeOptionResponse>> listAll() {
        List<FindDineDishAttributeOptionResponse> responses = dineDishAttributeOptionService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询菜品属性选项", description = "分页查询菜品属性选项")
    public Result<PageResult<FindDineDishAttributeOptionResponse>> pageQuery(@Valid DineDishAttributeOptionPageRequest request) {
        PageResult<FindDineDishAttributeOptionResponse> responses = dineDishAttributeOptionService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询菜品属性选项", description = "根据ID查询菜品属性选项")
    public Result<FindDineDishAttributeOptionResponse> getById(@PathVariable @NotNull Long id) {
        DineDishAttributeOption dineDishAttributeOption = dineDishAttributeOptionService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品属性选项不存在，ID：" + id));
        FindDineDishAttributeOptionResponse response = new FindDineDishAttributeOptionResponse();
        BeanUtils.copyProperties(dineDishAttributeOption, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建菜品属性选项", description = "创建菜品属性选项")
    public Result<FindDineDishAttributeOptionResponse> create(@Valid @RequestBody DineDishAttributeOptionRequest request) {
        FindDineDishAttributeOptionResponse response = dineDishAttributeOptionService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新菜品属性选项", description = "更新菜品属性选项")
    public Result<FindDineDishAttributeOptionResponse> update(@PathVariable @NotNull Long id,
                                                              @RequestBody @Valid DineDishAttributeOptionRequest request) {
        FindDineDishAttributeOptionResponse response = dineDishAttributeOptionService.update(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除菜品属性选项", description = "删除菜品属性选项")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        dineDishAttributeOptionService.delete(id);
        return Result.success();
    }
}