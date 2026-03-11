package com.basic.exception;

/**
 * Redis 分布式锁异常
 *
 * @author vains
 */
public class DistributedLockException extends CloudServiceException {

    public DistributedLockException(String msg) {
        super(msg);
    }
}
