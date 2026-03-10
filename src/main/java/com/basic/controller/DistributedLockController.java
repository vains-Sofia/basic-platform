package com.basic.controller;

import com.basic.annotation.DistributedLock;
import com.basic.domain.Result;
import com.basic.exception.CloudServiceException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Redis 分布式锁测试接口
 *
 * @author vains
 */
@Slf4j
@RestController
@Tag(name = "分布式锁测试接口")
@RequestMapping("/redis/lock")
public class DistributedLockController {

    private int publicResource = 10;

    @SneakyThrows
    @DistributedLock
    @GetMapping("/test01")
    public Result<Integer> test01() {
        TimeUnit.MILLISECONDS.sleep(100);
        if (publicResource <= 0) {
            throw new CloudServiceException("已售罄");
        }
        log.info("test01----->>>当前公共资源：{}", publicResource);
        return Result.success(--publicResource);
    }

    @SneakyThrows
    @DistributedLock
    @GetMapping("/test02")
    public Result<Integer> test02() {
        if (publicResource >= 20) {
            throw new CloudServiceException("超过限额");
        }
        log.info("test02----->>>当前公共资源：{}", publicResource);
        return Result.success(++publicResource);
    }

}
