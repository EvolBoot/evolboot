package org.evolboot.mq.redis.producer;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ExtendRuntimeException;
import org.evolboot.core.mq.DelayLevel;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.mq.core.domain.mqtransaction.MqTransactionAppService;
import org.evolboot.shared.event.mq.MQMessage;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class RedisMQMessagePublisher implements MQMessagePublisher {

    @Value("${spring.redis.mq-stream.key}")
    private String key;

    private final MqTransactionAppService mqTransactionAppService;

    private final MqMessageRedisTemplate mqMessageRedisTemplate;

    public RedisMQMessagePublisher(MqTransactionAppService mqTransactionAppService, MqMessageRedisTemplate mqMessageRedisTemplate) {
        this.mqTransactionAppService = mqTransactionAppService;
        this.mqMessageRedisTemplate = mqMessageRedisTemplate;
    }

    @Override
    public <T extends TransactionMQMessage> void sendMessageInTransaction(T message) {
        if (message.getMqTransactionId() == null) {
            message.setMqTransactionId(mqTransactionAppService.create().id());
        }
        mqMessageRedisTemplate.addMessage(key, message);
    }

    @Override
    public <T extends MQMessage> void send(T message) {
        mqMessageRedisTemplate.addMessage(key, message);
    }

    @Override
    public <T extends MQMessage> void send(T message, DelayLevel delayLevel) {
        throw new ExtendRuntimeException("Redis 暂不支持延迟消息");
    }

    @Override
    public <T extends MQMessage> void sendDelayTimeSeconds(T message, long delayTime) {
        throw new ExtendRuntimeException("Redis 暂不支持延迟消息");
    }
}
