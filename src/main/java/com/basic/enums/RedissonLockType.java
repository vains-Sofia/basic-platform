package com.basic.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * 分布式锁类型
 *
 * @author vains
 */
@Getter
@RequiredArgsConstructor
public enum RedissonLockType implements BasicEnum<String, RedissonLockType> {

    /**
     * 普通分布式锁
     */
    R_LOCK("普通分布式锁", "RLock") {
        @Override
        public RLock getLock(RedissonClient redisson, String lockKey) {
            return redisson.getLock(lockKey);
        }
    },

    /**
     * 自旋锁
     */
    SPIN_LOCK("自旋锁", "SpinLock") {
        @Override
        public RLock getLock(RedissonClient redisson, String lockKey) {
            return redisson.getSpinLock(lockKey);
        }
    },

    /**
     * 栅栏锁
     */
    FENCED_LOCK("栅栏锁", "FencedLock") {
        @Override
        public RLock getLock(RedissonClient redisson, String lockKey) {
            return redisson.getFencedLock(lockKey);
        }
    },

    /**
     * 公平锁
     */
    R_FAIR_LOCK("公平分布式锁", "RFairLock") {
        @Override
        public RLock getLock(RedissonClient redisson, String lockKey) {
            return redisson.getFairLock(lockKey);
        }
    },

    /**
     * 读锁
     */
    R_READ_LOCK("读锁", "RReadLock") {
        @Override
        public RLock getLock(RedissonClient redisson, String lockKey) {
            return redisson.getReadWriteLock(lockKey).readLock();
        }
    },

    /**
     * 写锁
     */
    R_WRITE_LOCK("写锁", "RWriteLock") {
        @Override
        public RLock getLock(RedissonClient redisson, String lockKey) {
            return redisson.getReadWriteLock(lockKey).writeLock();
        }
    };

    /**
     * 分布式锁的描述
     */
    private final String description;

    /**
     * 分布式锁的名称
     */
    private final String lockType;

    public String getValue() {
        return this.lockType;
    }

    /**
     * 默认的获取锁的操作，由子类重写提供
     *
     * @param redisson 分布式锁的Java实现{@link RedissonClient}实例
     * @param lockKey  分布式锁的key
     * @return 获取到的分布式锁实例
     */
    public RLock getLock(RedissonClient redisson, String lockKey) {
        throw new UnsupportedOperationException("该锁类型不支持此操作");
    }
}
