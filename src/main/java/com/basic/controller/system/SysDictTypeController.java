package com.basic.controller.system;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.SysDictType;
import com.basic.domain.request.SysDictTypePageRequest;
import com.basic.domain.request.SysDictTypeRequest;
import com.basic.domain.response.FindSysDictTypeResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典类型相关接口
 *
 * @author vains
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict/type")
@Tag(name = "字典 api 接口", description = "字典类型、字典项接口")
public class SysDictTypeController {

    private final SysDictTypeService sysDictTypeService;

    @GetMapping("/all")
    @Operation(summary = "查询所有字典项", description = "查询所有字典项")
    public Result<List<FindSysDictTypeResponse>> listAll() {
        List<FindSysDictTypeResponse> responses = sysDictTypeService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询所有字典类型", description = "分页查询所有字典类型")
    public Result<PageResult<FindSysDictTypeResponse>> pageQuery(@Valid SysDictTypePageRequest request) {
        PageResult<FindSysDictTypeResponse> responses = sysDictTypeService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据字典 类型ID 查询字典类型", description = "根据字典 类型ID 查询字典类型")
    public Result<FindSysDictTypeResponse> getById(@PathVariable @NotNull Long id) {
        SysDictType sysDictType = sysDictTypeService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("字典类型不存在，ID：" + id));

        FindSysDictTypeResponse response = new FindSysDictTypeResponse();
        BeanUtils.copyProperties(sysDictType, response);

        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建字典类型", description = "创建字典类型")
    public Result<FindSysDictTypeResponse> create(@Valid @RequestBody SysDictTypeRequest request) {
        FindSysDictTypeResponse response = sysDictTypeService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新字典类型", description = "更新字典类型")
    public Result<FindSysDictTypeResponse> updateDictType(@PathVariable @NotNull Long id,
                                           @RequestBody @Valid SysDictTypeRequest request) {
        FindSysDictTypeResponse response = sysDictTypeService.updateDictType(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除字典类型", description = "删除字典类型")
    public Result<String> delete(@PathVariable Long id) {
        sysDictTypeService.delete(id);
        return Result.success();
    }

}
