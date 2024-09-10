package org.evolboot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author nlx
 * @ClassName RedissonTemplate
 * redisson封装操作类
 */
//@Configuration
@Slf4j
@Service
public class RedissonTemplate {


    private final RedissonClient redissonClient;


    /**
     * 锁前缀
     */
    private final String DEFAULT_LOCK_NAME = "nlx-instance";


    public RedissonTemplate(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    /**
     * 加锁（可重入），会一直等待获取锁，不会中断
     *
     * @param lockName waitTimeout  timeout
     * @return boolean
     * @author ymy
     * @date 2021/5/13 17:53
     */
    public boolean lock(String lockName, long timeout) {
        checkRedissonClient();
        RLock lock = getLock(lockName);
        try {
            if (timeout != -1) {
                // timeout:超时时间   TimeUnit.SECONDS：单位
                lock.lock(timeout, TimeUnit.SECONDS);
            } else {
                lock.lock();
            }
            log.debug(" get lock success ,lockKey:{}", lockName);
            return true;
        } catch (Exception e) {
            log.error(" get lock fail,lockKey:{}, cause:{} ",
                    lockName, e.getMessage());
            return false;
        }
    }


    /**
     * 可中断锁
     *
     * @param lockName    锁名称
     * @param waitTimeout 等待时长
     * @param unit        时间单位
     * @return
     */
    public boolean tryLock(String lockName, long waitTimeout, TimeUnit unit) {
        checkRedissonClient();
        RLock lock = getLock(lockName);
        try {
            boolean res = lock.tryLock(waitTimeout, unit);
            if (!res) {
                log.debug(" get lock fail ,lockKey:{}", lockName);
                return false;
            }
            log.debug(" get lock success ,lockKey:{}", lockName);
            return true;
        } catch (Exception e) {
            log.error(" get lock fail,lockKey:{}, cause:{} ",
                    lockName, e.getMessage());
            return false;
        }
    }

    /**
     * 公平锁
     *
     * @param lockName
     * @param waitTimeout
     * @param timeout
     * @param unit
     * @return
     */
    public boolean getFairLock(String lockName, long waitTimeout, long timeout, TimeUnit unit) {
        checkRedissonClient();
        RLock lock = redissonClient.getFairLock(DEFAULT_LOCK_NAME + lockName);
        try {
            boolean res = lock.tryLock(waitTimeout, timeout, unit);
            if (!res) {
                log.debug(" get lock fail ,lockKey:{}", lockName);
                return false;
            }
            log.debug(" get lock success ,lockKey:{}", lockName);
            return true;
        } catch (Exception e) {
            log.error(" get lock fail,lockKey:{}, cause:{} ",
                    lockName, e.getMessage());
            return false;
        }
    }


    /**
     * 解锁
     *
     * @param lockName
     */
    public void unlock(String lockName) {
        checkRedissonClient();
        try {
            RLock lock = redissonClient.getFairLock(DEFAULT_LOCK_NAME + lockName);
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.debug("key：{}，unlock success", lockName);
            } else {
                log.debug("key：{}，没有加锁或者不是当前线程加的锁 ", lockName);
            }
        } catch (Exception e) {
            log.error("key：{}，unlock error,reason:{}", lockName, e.getMessage());
        }
    }


    public RLock getLock(String lockName) {
        String key = DEFAULT_LOCK_NAME + lockName;
        return redissonClient.getLock(key);
    }


    private void checkRedissonClient() {
        if (null == redissonClient) {
            log.error(" redissonClient is null ,please check redis instance ! ");
            throw new RuntimeException("redissonClient is null ,please check redis instance !");
        }
        if (redissonClient.isShutdown()) {
            log.error(" Redisson instance has been shut down !!!");
            throw new RuntimeException("Redisson instance has been shut down !!!");
        }
    }


    /**
     * 获取读写锁
     *
     * @param lockName
     * @return
     */
    public RReadWriteLock getReadWriteLock(String lockName) {
        return redissonClient.getReadWriteLock(lockName);

    }

    /**
     * 信号量
     *
     * @param semaphoreName
     * @return
     */
    public RSemaphore getSemaphore(String semaphoreName) {
        return redissonClient.getSemaphore(semaphoreName);
    }

    /**
     * 可过期性信号量
     *
     * @param permitExpirableSemaphoreName
     * @return
     */
    public RPermitExpirableSemaphore getPermitExpirableSemaphore(String permitExpirableSemaphoreName) {

        return redissonClient.getPermitExpirableSemaphore(permitExpirableSemaphoreName);
    }

    /**
     * 闭锁
     *
     * @param countDownLatchName
     * @return
     */
    public RCountDownLatch getCountDownLatch(String countDownLatchName) {
        return redissonClient.getCountDownLatch(countDownLatchName);
    }


    /**
     * 获取队列名称
     *
     * @param name
     * @return
     */
    public <V> RBlockingQueue<V> getBlockingQueue(String name) {
        return redissonClient.getBlockingQueue(name);
    }

    /**
     * 获取队列名称
     *
     * @param name
     * @return
     */
    public <V> RDelayedQueue<V> getDelayedQueue(String name) {
        return redissonClient.getDelayedQueue(getBlockingQueue(name));
    }


    /**
     * 获取队列名称
     *
     * @param name
     * @return
     */
    public <V> RDeque<V> getDeque(String name) {
        return redissonClient.getDeque(name);
    }

    /**
     * 获取队列名称
     *
     * @param name
     * @return
     */
    public <V> RSet<V> getSet(String name) {
        return redissonClient.getSet(name);
    }
}

