package com.basic.exception;

/**
 * Redis 分布式锁异常
 *
 * @author vains
 */
public class RedisLockException extends CloudServiceException {

    public RedisLockException(String msg) {
        super(msg);
    }
}
