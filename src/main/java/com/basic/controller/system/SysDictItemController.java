package com.basic.controller.system;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.SysDictItem;
import com.basic.domain.request.SysDictItemPageRequest;
import com.basic.domain.request.SysDictItemRequest;
import com.basic.domain.response.FindSysDictItemResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.SysDictItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典项相关接口
 *
 * @author vains
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict/item")
@Tag(name = "字典 api 接口", description = "字典类型、字典项接口")
public class SysDictItemController {

    private final SysDictItemService sysDictItemService;

    @GetMapping("/page")
    @Operation(summary = "分页查询字典项", description = "分页查询字典项")
    public Result<PageResult<FindSysDictItemResponse>> pageQuery(@Valid SysDictItemPageRequest request) {
        PageResult<FindSysDictItemResponse> pageResult = sysDictItemService.pageQuery(request);
        return Result.success(pageResult);
    }

    @GetMapping("/type/{typeCode}")
    @Operation(summary = "根据字典类型编码查询字典项", description = "根据字典类型编码查询字典项")
    public Result<List<FindSysDictItemResponse>> listByType(@PathVariable @NotBlank String typeCode) {
        List<FindSysDictItemResponse> responses = sysDictItemService.listByType(typeCode);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据 字典项ID 查询字典项", description = "根据 字典项ID 查询字典项")
    public Result<FindSysDictItemResponse> getById(@PathVariable @NotNull Long id) {
        SysDictItem sysDictItem = sysDictItemService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("字典项不存在，ID：" + id));

        FindSysDictItemResponse response = new FindSysDictItemResponse();
        BeanUtils.copyProperties(sysDictItem, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建字典项", description = "创建字典项")
    public Result<FindSysDictItemResponse> create(@RequestBody @Valid SysDictItemRequest request) {
        FindSysDictItemResponse response = sysDictItemService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新字典项", description = "更新字典项")
    public Result<FindSysDictItemResponse> updateDictItem(@PathVariable @NotNull Long id,
                                                          @RequestBody @Valid SysDictItemRequest request) {
        FindSysDictItemResponse response = sysDictItemService.updateDictItem(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除字典项", description = "删除字典项")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        sysDictItemService.removeById(id);
        return Result.success();
    }

}
