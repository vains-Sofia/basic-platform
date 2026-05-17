package com.basic.controller.dine;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.DineTableInfo;
import com.basic.domain.request.DineTableInfoPageRequest;
import com.basic.domain.request.DineTableInfoRequest;
import com.basic.domain.response.FindDineTableInfoResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.DineTableInfoService;
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
@RequestMapping("/order/table/info")
@Tag(name = "点单-桌位信息 api 接口", description = "桌位信息相关接口")
public class DineTableInfoController {

    private final DineTableInfoService dineTableInfoService;

    @GetMapping("/all")
    @Operation(summary = "查询所有桌位信息", description = "查询所有桌位信息")
    public Result<List<FindDineTableInfoResponse>> listAll() {
        List<FindDineTableInfoResponse> responses = dineTableInfoService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询桌位信息", description = "分页查询桌位信息")
    public Result<PageResult<FindDineTableInfoResponse>> pageQuery(@Valid DineTableInfoPageRequest request) {
        PageResult<FindDineTableInfoResponse> responses = dineTableInfoService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询桌位信息", description = "根据ID查询桌位信息")
    public Result<FindDineTableInfoResponse> getById(@PathVariable @NotNull Long id) {
        DineTableInfo dineTableInfo = dineTableInfoService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("桌位信息不存在，ID：" + id));
        FindDineTableInfoResponse response = new FindDineTableInfoResponse();
        BeanUtils.copyProperties(dineTableInfo, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建桌位信息", description = "创建桌位信息")
    public Result<FindDineTableInfoResponse> create(@Valid @RequestBody DineTableInfoRequest request) {
        FindDineTableInfoResponse response = dineTableInfoService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新桌位信息", description = "更新桌位信息")
    public Result<FindDineTableInfoResponse> update(@PathVariable @NotNull Long id,
                                                    @RequestBody @Valid DineTableInfoRequest request) {
        FindDineTableInfoResponse response = dineTableInfoService.update(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除桌位信息", description = "删除桌位信息")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        dineTableInfoService.delete(id);
        return Result.success();
    }
}