package com.basic.controller.dine;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.DineStore;
import com.basic.domain.request.DineStorePageRequest;
import com.basic.domain.request.DineStoreRequest;
import com.basic.domain.response.FindDineInfoResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.DineStoreService;
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
@RequestMapping("/store/info")
@Tag(name = "点单-店铺信息 api 接口", description = "店铺信息相关接口")
public class DineStoreController {

    private final DineStoreService dineStoreService;

    @GetMapping("/all")
    @Operation(summary = "查询所有店铺信息", description = "查询所有店铺信息")
    public Result<List<FindDineInfoResponse>> listAll() {
        List<FindDineInfoResponse> responses = dineStoreService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询店铺信息", description = "分页查询店铺信息")
    public Result<PageResult<FindDineInfoResponse>> pageQuery(@Valid DineStorePageRequest request) {
        PageResult<FindDineInfoResponse> responses = dineStoreService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询店铺信息", description = "根据ID查询店铺信息")
    public Result<FindDineInfoResponse> getById(@PathVariable @NotNull Long id) {
        DineStore dineStore = dineStoreService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("店铺信息不存在，ID：" + id));
        FindDineInfoResponse response = new FindDineInfoResponse();
        BeanUtils.copyProperties(dineStore, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建店铺信息", description = "创建店铺信息")
    public Result<FindDineInfoResponse> create(@Valid @RequestBody DineStoreRequest request) {
        FindDineInfoResponse response = dineStoreService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新店铺信息", description = "更新店铺信息")
    public Result<FindDineInfoResponse> update(@PathVariable @NotNull Long id,
                                               @RequestBody @Valid DineStoreRequest request) {
        FindDineInfoResponse response = dineStoreService.update(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除店铺信息", description = "删除店铺信息")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        dineStoreService.delete(id);
        return Result.success();
    }
}