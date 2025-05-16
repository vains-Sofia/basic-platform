package com.basic.annotation;

import com.basic.enums.RedissonLockType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁注解
 *
 * @author vains
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {

    /**
     * 分布式锁的key，支持使用spEl表达式
     *
     * @return 分布式锁的key
     */
    String value() default "";

    /**
     * 租约时间,如果当前线程成功获取到锁，那么锁将被持有的时间长度。这个时间过后，锁会自动释放，默认为 -1(代表不指定，如果指定则看门狗(watchdog)不会自动续约)
     *
     * @return 租约时间,如果当前线程成功获取到锁，那么锁将被持有的时间长度。这个时间过后，锁会自动释放
     */
    long leaseTime() default -1;

    /**
     * 等待获取锁的最长时间，默认为 3
     *
     * @return 加锁时间
     */
    long waitTime() default 3;

    /**
     * 时间单位，默认为 秒(s)
     *
     * @return 时间单位，默认为 秒(s)
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 错误提示消息
     *
     * @return 错误提示消息
     */
    String message() default "请求过快，请稍后重试";

    /**
     * 分布式锁类型，目前支持的类型请查看{@link RedissonLockType}
     *
     * @return 分布式锁类型
     */
    RedissonLockType lockType() default RedissonLockType.R_LOCK;

}
