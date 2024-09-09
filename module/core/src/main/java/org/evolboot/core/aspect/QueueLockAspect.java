package org.evolboot.core.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.evolboot.core.annotation.QueueLock;
import org.evolboot.core.service.RedissonTemplate;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.SpelUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 队列锁
 *
 * @author evol
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class QueueLockAspect {

    private final RedissonTemplate redissonTemplate;


    private final static String REDIS_KEY = "QL::";

    public QueueLockAspect(RedissonTemplate redissonTemplate) {
        this.redissonTemplate = redissonTemplate;
    }

    @Around("@annotation(queueLock)")
    public Object around(ProceedingJoinPoint pjp, QueueLock queueLock) throws Throwable {
        if (ExtendObjects.isBlank(queueLock.key())) {
            log.error("队列锁:没有填写对应的Key:{}", queueLock.taskName());
            return pjp.proceed();
        }
        String lockName = SpelUtil.generateKeyBySpEL(queueLock.key(), pjp);
        if (ExtendObjects.isBlank(lockName)) {
            log.error("队列锁:对应的Key为空:{},{}", queueLock.taskName(), lockName);
            return pjp.proceed();
        }
        lockName = REDIS_KEY + queueLock.keyPrefix() + lockName;
        boolean fairLock = redissonTemplate.getFairLock(lockName, queueLock.timeout(), queueLock.timeout(), queueLock.timeUnit());
        log.info("队列锁:是否抢到锁:{},{},{}", queueLock.taskName(), lockName, fairLock);
        try {
            return pjp.proceed();
        } catch (Exception e) {
            log.error("队列锁:执行报错:{},{}", queueLock.taskName(), lockName, e);
            throw e;
        } finally {
            log.info("队列锁:执行完成:解锁:{},{}", queueLock.taskName(), lockName);
            redissonTemplate.unlock(lockName);
        }
    }


}
