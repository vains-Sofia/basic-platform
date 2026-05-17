package com.basic.controller.dine;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.DineAttributeOption;
import com.basic.domain.request.DineAttributeOptionPageRequest;
import com.basic.domain.request.DineAttributeOptionRequest;
import com.basic.domain.response.FindDineAttributeOptionResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.DineAttributeOptionService;
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
@RequestMapping("/order/attribute/option")
@Tag(name = "点单-属性选项 api 接口", description = "属性选项相关接口")
public class DineAttributeOptionController {

    private final DineAttributeOptionService dineAttributeOptionService;

    @GetMapping("/all")
    @Operation(summary = "查询所有属性选项", description = "查询所有属性选项")
    public Result<List<FindDineAttributeOptionResponse>> listAll() {
        List<FindDineAttributeOptionResponse> responses = dineAttributeOptionService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询属性选项", description = "分页查询属性选项")
    public Result<PageResult<FindDineAttributeOptionResponse>> pageQuery(@Valid DineAttributeOptionPageRequest request) {
        PageResult<FindDineAttributeOptionResponse> responses = dineAttributeOptionService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询属性选项", description = "根据ID查询属性选项")
    public Result<FindDineAttributeOptionResponse> getById(@PathVariable @NotNull Long id) {
        DineAttributeOption dineAttributeOption = dineAttributeOptionService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("属性选项不存在，ID：" + id));
        FindDineAttributeOptionResponse response = new FindDineAttributeOptionResponse();
        BeanUtils.copyProperties(dineAttributeOption, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建属性选项", description = "创建属性选项")
    public Result<FindDineAttributeOptionResponse> create(@Valid @RequestBody DineAttributeOptionRequest request) {
        FindDineAttributeOptionResponse response = dineAttributeOptionService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新属性选项", description = "更新属性选项")
    public Result<FindDineAttributeOptionResponse> update(@PathVariable @NotNull Long id,
                                                          @RequestBody @Valid DineAttributeOptionRequest request) {
        FindDineAttributeOptionResponse response = dineAttributeOptionService.update(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除属性选项", description = "删除属性选项")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        dineAttributeOptionService.delete(id);
        return Result.success();
    }
}