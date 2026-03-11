package com.basic.controller;

import com.basic.domain.Result;
import com.basic.domain.entity.SysDictType;
import com.basic.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.basic.constant.AuthorizeConstants.BASIC_PREFIX;

/**
 * 测试接口
 *
 * @author vains
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test01")
@Tag(name = "测试接口", description = "测试接口")
public class Test01Controller {

    private final SysDictTypeService sysDictTypeService;

    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test01")
    @Operation(summary = "测试接口-01", description = "测试接口-01")
    public String test01() {
        redisTemplate.opsForValue().set(BASIC_PREFIX.concat("test01"), "test");
        return redisTemplate.opsForValue().get(BASIC_PREFIX.concat("test01"));
    }

    @GetMapping("/test02")
    @Operation(summary = "测试接口-02", description = "测试接口-02")
    public String test02() {
        redisTemplate.opsForValue().set(BASIC_PREFIX.concat("test02"), "test");
        String test = redisTemplate.opsForValue().get(BASIC_PREFIX.concat("test02"));
        System.out.println(test);
        return test;
    }

    @GetMapping("/test03")
    @PreAuthorize("hasAuthority('CHECK')")
    @Operation(summary = "测试接口-03", description = "测试接口-03")
    public String test03() {
        redisTemplate.opsForValue().set(BASIC_PREFIX.concat("test03"), "test");
        String test = redisTemplate.opsForValue().get(BASIC_PREFIX.concat("test03"));
        System.out.println(test);
        return test;
    }

    @GetMapping("/dict/types")
    @PreAuthorize("hasAuthority('dict:type')")
    @Operation(summary = "测试接口-04", description = "测试接口-04")
    public Result<List<SysDictType>> dictTypes() {

        return Result.success(sysDictTypeService.list());
    }

}
