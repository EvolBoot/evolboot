package org.evolboot.mq.redis.consumer.task;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.core.domain.mqtransaction.MqTransactionAppService;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.evolboot.mq.redis.producer.RedisMQMessagePublisher;
import org.evolboot.mq.redis.producer.RedisStreamProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author evol
 */
@Service
@Slf4j
public class RedisMQMessageScheduledTask {

    private final RedisStreamProperty redisStreamProperty;
    private final MqMessageRedisTemplate mqMessageRedisTemplate;
    private final MqTransactionAppService mqTransactionAppService;
    private final RedisMQMessagePublisher redisMQMessagePublisher;
    private ScheduledExecutorService executorService;


    public RedisMQMessageScheduledTask(RedisStreamProperty redisStreamProperty,
                                       MqMessageRedisTemplate mqMessageRedisTemplate,
                                       MqTransactionAppService mqTransactionAppService, RedisMQMessagePublisher redisMQMessagePublisher) {
        this.redisStreamProperty = redisStreamProperty;
        this.mqMessageRedisTemplate = mqMessageRedisTemplate;
        this.mqTransactionAppService = mqTransactionAppService;

        this.redisMQMessagePublisher = redisMQMessagePublisher;
    }

    public void init() {
        this.executorService = Executors.newScheduledThreadPool(2);

        // 事务消息处理器
        RedisMQMessageHandle redisMQTransactionMessageHandle = new RedisMQMessageHandle(
                mqMessageRedisTemplate, mqTransactionAppService, redisMQMessagePublisher, redisStreamProperty.getKeyForTransaction(), redisStreamProperty.getGroup()
        );
        // 延时消息处理器
        RedisMQMessageHandle redisMQDelayMessageHandle = new RedisMQMessageHandle(
                mqMessageRedisTemplate, mqTransactionAppService, redisMQMessagePublisher, redisStreamProperty.getKeyForDelayTime(), redisStreamProperty.getGroup()
        );

        // 实时消息处理器
        RedisMQMessageHandle redisMQRealMessageHandle = new RedisMQMessageHandle(
                mqMessageRedisTemplate, mqTransactionAppService, redisMQMessagePublisher, redisStreamProperty.getKeyForRealTime(), redisStreamProperty.getGroup()
        );
        executorService.scheduleAtFixedRate(redisMQTransactionMessageHandle, 5, 5, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(redisMQDelayMessageHandle, 5, 5, TimeUnit.SECONDS);

        // 实时消息只需要启动时处理一次
        log.info("消息队列:Redis:实时消息:处理未完成的消息");
        redisMQRealMessageHandle.handlePendingMessage();
    }

}
