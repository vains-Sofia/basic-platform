package com.basic.controller.dine;

import com.basic.domain.PageResult;
import com.basic.domain.Result;
import com.basic.domain.entity.DineDish;
import com.basic.domain.request.DineDishPageRequest;
import com.basic.domain.request.DineDishRequest;
import com.basic.domain.response.FindDineDishResponse;
import com.basic.exception.CloudServiceException;
import com.basic.service.DineDishService;
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
@RequestMapping("/order/dish")
@Tag(name = "点单-菜品 api 接口", description = "菜品相关接口")
public class DineDishController {

    private final DineDishService dineDishService;

    @GetMapping("/all")
    @Operation(summary = "查询所有菜品", description = "查询所有菜品")
    public Result<List<FindDineDishResponse>> listAll() {
        List<FindDineDishResponse> responses = dineDishService.listAll();
        return Result.success(responses);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询菜品", description = "分页查询菜品")
    public Result<PageResult<FindDineDishResponse>> pageQuery(@Valid DineDishPageRequest request) {
        PageResult<FindDineDishResponse> responses = dineDishService.pageQuery(request);
        return Result.success(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询菜品", description = "根据ID查询菜品")
    public Result<FindDineDishResponse> getById(@PathVariable @NotNull Long id) {
        DineDish dineDish = dineDishService.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品不存在，ID：" + id));
        FindDineDishResponse response = new FindDineDishResponse();
        BeanUtils.copyProperties(dineDish, response);
        return Result.success(response);
    }

    @PostMapping
    @Operation(summary = "创建菜品", description = "创建菜品")
    public Result<FindDineDishResponse> create(@Valid @RequestBody DineDishRequest request) {
        FindDineDishResponse response = dineDishService.create(request);
        return Result.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新菜品", description = "更新菜品")
    public Result<FindDineDishResponse> update(@PathVariable @NotNull Long id,
                                               @RequestBody @Valid DineDishRequest request) {
        FindDineDishResponse response = dineDishService.update(id, request);
        return Result.success(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除菜品", description = "删除菜品")
    public Result<String> delete(@PathVariable @NotNull Long id) {
        dineDishService.delete(id);
        return Result.success();
    }
}