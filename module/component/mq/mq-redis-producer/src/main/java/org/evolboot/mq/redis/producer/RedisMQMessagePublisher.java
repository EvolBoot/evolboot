package org.evolboot.mq.redis.producer;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ExtendRuntimeException;
import org.evolboot.core.mq.DelayLevel;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.core.util.Assert;
import org.evolboot.mq.core.domain.mqtransaction.MqTransactionAppService;
import org.evolboot.shared.event.mq.DelayMQMessage;
import org.evolboot.shared.event.mq.MQMessage;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.springframework.stereotype.Component;

/**
 * Redis 消息队列 发送消息
 *
 * @author evol
 */
@Component
@Slf4j
public class RedisMQMessagePublisher implements MQMessagePublisher {

    private final RedisStreamProperty redisStreamProperty;

    private final MqTransactionAppService mqTransactionAppService;

    private final MqMessageRedisTemplate mqMessageRedisTemplate;

    private long TIMEOUT_DELAY_TIME_SECONDS = 24 * 60 * 60;

    public RedisMQMessagePublisher(RedisStreamProperty redisStreamProperty, MqTransactionAppService mqTransactionAppService, MqMessageRedisTemplate mqMessageRedisTemplate) {
        this.redisStreamProperty = redisStreamProperty;
        this.mqTransactionAppService = mqTransactionAppService;
        this.mqMessageRedisTemplate = mqMessageRedisTemplate;
    }

    @Override
    public <T extends TransactionMQMessage> void sendMessageInTransaction(T message) {
        if (message.getMessageCreateTimestamp() == null) {
            message.setMessageCreateTimestamp(System.currentTimeMillis());
        }
        if (message.getMqTransactionId() == null) {
            message.setMqTransactionId(mqTransactionAppService.create().id());
        }
        mqMessageRedisTemplate.addMessage(redisStreamProperty.getKeyForTransaction(), message);
    }

    @Override
    public <T extends MQMessage> void send(T message) {
        if (message.getMessageCreateTimestamp() == null) {
            message.setMessageCreateTimestamp(System.currentTimeMillis());
        }
        message.setMessageCreateTimestamp(System.currentTimeMillis());
        mqMessageRedisTemplate.addMessage(redisStreamProperty.getKeyForRealTime(), message);
    }

    @Override
    public <T extends MQMessage> void send(T message, DelayLevel delayLevel) {
        if (message.getMessageCreateTimestamp() == null) {
            message.setMessageCreateTimestamp(System.currentTimeMillis());
        }
        if (message instanceof DelayMQMessage) {
            Assert.isTrue(delayLevel.getDelayTimeSeconds() < TIMEOUT_DELAY_TIME_SECONDS, "仅支持" + TIMEOUT_DELAY_TIME_SECONDS + "秒内的延时消息");
            ((DelayMQMessage<?>) message).setDelayTimeSeconds(delayLevel.getDelayTimeSeconds());
            mqMessageRedisTemplate.addMessage(redisStreamProperty.getKeyForDelayTime(), message);
            return;
        }
        throw new ExtendRuntimeException("Redis 仅支持继承了DelayMQMessage的延时消息");
    }

    @Override
    public <T extends MQMessage> void sendDelayTimeSeconds(T message, long delayTime) {
        if (message.getMessageCreateTimestamp() == null) {
            message.setMessageCreateTimestamp(System.currentTimeMillis());
        }
        if (message instanceof DelayMQMessage) {
            Assert.isTrue(delayTime < TIMEOUT_DELAY_TIME_SECONDS, "仅支持" + TIMEOUT_DELAY_TIME_SECONDS + "秒内的延时消息");
            ((DelayMQMessage<?>) message).setDelayTimeSeconds(delayTime);
            mqMessageRedisTemplate.addMessage(redisStreamProperty.getKeyForDelayTime(), message);
            return;
        }
        throw new ExtendRuntimeException("Redis 仅支持继承了DelayMQMessage的延时消息");
    }
}
