package com.basic.controller.dine;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.DineCategory;
import com.basic.domain.request.DineCategoryPageRequest;
import com.basic.domain.request.DineCategoryRequest;
import com.basic.domain.response.FindDineCategoryResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.DineCategoryService;
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
@RequestMapping("/order/category")
@Tag(name = "点单-分类 api 接口", description = "分类相关接口")
public class DineCategoryController {

    private final DineCategoryService dineCategoryService;

    @GetMapping("/all")
    @Operation(summary = "查询所有分类", description = "查询所有分类")
    public Result<List<FindDineCategoryResponse>> listAll() {
        List<FindDineCategoryResponse> responses = dineCategoryService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询分类", description = "分页查询分类")
    public Result<PageResult<FindDineCategoryResponse>> pageQuery(@Valid DineCategoryPageRequest request) {
        PageResult<FindDineCategoryResponse> responses = dineCategoryService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询分类", description = "根据ID查询分类")
    public Result<FindDineCategoryResponse> getById(@PathVariable @NotNull Long id) {
        DineCategory dineCategory = dineCategoryService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("分类不存在，ID：" + id));
        FindDineCategoryResponse response = new FindDineCategoryResponse();
        BeanUtils.copyProperties(dineCategory, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建分类", description = "创建分类")
    public Result<FindDineCategoryResponse> create(@Valid @RequestBody DineCategoryRequest request) {
        FindDineCategoryResponse response = dineCategoryService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新分类", description = "更新分类")
    public Result<FindDineCategoryResponse> update(@PathVariable @NotNull Long id,
                                                   @RequestBody @Valid DineCategoryRequest request) {
        FindDineCategoryResponse response = dineCategoryService.update(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除分类", description = "删除分类")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        dineCategoryService.delete(id);
        return Result.success();
    }
}