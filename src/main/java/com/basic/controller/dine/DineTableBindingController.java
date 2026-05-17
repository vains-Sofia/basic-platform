package com.basic.controller.dine;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.DineTableBinding;
import com.basic.domain.request.DineTableBindingPageRequest;
import com.basic.domain.request.DineTableBindingRequest;
import com.basic.domain.response.FindDineTableBindingResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.DineTableBindingService;
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
@RequestMapping("/order/table/binding")
@Tag(name = "点单-桌位绑定 api 接口", description = "桌位绑定相关接口")
public class DineTableBindingController {

    private final DineTableBindingService dineTableBindingService;

    @GetMapping("/all")
    @Operation(summary = "查询所有桌位绑定", description = "查询所有桌位绑定")
    public Result<List<FindDineTableBindingResponse>> listAll() {
        List<FindDineTableBindingResponse> responses = dineTableBindingService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询桌位绑定", description = "分页查询桌位绑定")
    public Result<PageResult<FindDineTableBindingResponse>> pageQuery(@Valid DineTableBindingPageRequest request) {
        PageResult<FindDineTableBindingResponse> responses = dineTableBindingService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询桌位绑定", description = "根据ID查询桌位绑定")
    public Result<FindDineTableBindingResponse> getById(@PathVariable @NotNull Long id) {
        DineTableBinding dineTableBinding = dineTableBindingService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("桌位绑定不存在，ID：" + id));
        FindDineTableBindingResponse response = new FindDineTableBindingResponse();
        BeanUtils.copyProperties(dineTableBinding, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建桌位绑定", description = "创建桌位绑定")
    public Result<FindDineTableBindingResponse> create(@Valid @RequestBody DineTableBindingRequest request) {
        FindDineTableBindingResponse response = dineTableBindingService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新桌位绑定", description = "更新桌位绑定")
    public Result<FindDineTableBindingResponse> update(@PathVariable @NotNull Long id,
                                                       @RequestBody @Valid DineTableBindingRequest request) {
        FindDineTableBindingResponse response = dineTableBindingService.update(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除桌位绑定", description = "删除桌位绑定")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        dineTableBindingService.delete(id);
        return Result.success();
    }
}