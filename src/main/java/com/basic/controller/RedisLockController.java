package com.basic.controller;

import com.basic.annotation.RedisLock;
import com.basic.domain.Result;
import com.basic.exception.CloudServiceException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis分布式锁测试接口
 *
 * @author vains
 */
@Slf4j
@RestController
@RequestMapping("/redis/lock")
public class RedisLockController {

    private int publicResource = 10;

    @RedisLock
    @SneakyThrows
    @GetMapping("/test01")
    public Result<Integer> test01() {
        if (publicResource <= 0) {
            throw new CloudServiceException("已售罄");
        }
        log.info("test01----->>>当前公共资源：{}", publicResource);
        return Result.success(--publicResource);
    }

    @RedisLock
    @SneakyThrows
    @GetMapping("/test02")
    public Result<Integer> test02() {
        if (publicResource >= 20) {
            throw new CloudServiceException("超过限额");
        }
        log.info("test02----->>>当前公共资源：{}", publicResource);
        return Result.success(++publicResource);
    }

}
