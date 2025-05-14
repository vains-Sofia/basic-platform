package com.basic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author vains
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "测试接口", description = "测试接口")
public class Test01Controller {

    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test01")
    @Operation(summary = "测试接口-01", description = "测试接口-01")
    public void test01() {
        redisTemplate.opsForValue().set("test", "test");
        String test = redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }


}
