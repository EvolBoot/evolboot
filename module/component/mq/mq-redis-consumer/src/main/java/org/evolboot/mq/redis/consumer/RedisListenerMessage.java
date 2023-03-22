package org.evolboot.mq.redis.consumer;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.mq.core.domain.mqtransaction.MqTransactionAppService;
import org.evolboot.mq.redis.consumer.autoconfigure.RedisStreamProperty;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.evolboot.shared.event.mq.MQMessage;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author linjie
 */
@Component
@Slf4j
public class RedisListenerMessage implements StreamListener<String, MapRecord<String, String, String>> {

    private final RedisStreamProperty redisStreamProperty;
    private final EventPublisher eventPublisher;
    private final MqMessageRedisTemplate mqMessageRedisTemplate;
    private final MqTransactionAppService mqTransactionAppService;

    public RedisListenerMessage(RedisStreamProperty redisStreamProperty, EventPublisher eventPublisher, MqMessageRedisTemplate mqMessageRedisTemplate, MqTransactionAppService mqTransactionAppService) {
        this.redisStreamProperty = redisStreamProperty;
        this.eventPublisher = eventPublisher;
        this.mqMessageRedisTemplate = mqMessageRedisTemplate;
        this.mqTransactionAppService = mqTransactionAppService;
    }


    @Override
    @Transactional
    public void onMessage(MapRecord<String, String, String> message) {
        log.info("消息队列:Redis:收到消息:{},{}", message.getStream(), message.getId());
        try {
            String key = message.getValue().keySet().stream().findFirst().get();
            log.info("消息队列:Redis:收到消息:{},{},{},{}", message.getStream(), message.getId(), key, message.getValue().get(key));
            MQMessage mqMessage = (MQMessage) JsonUtil.parse(message.getValue().get(key), getMessageClass(key));
            if (mqMessage instanceof TransactionMQMessage) {
                TransactionMQMessage transactionMQMessage = (TransactionMQMessage) mqMessage;
                log.info("消息队列:Redis:事务消息:查询事务是否提交:事务ID:{}", transactionMQMessage.getMqTransactionId());
                if (!mqTransactionAppService.existsById(transactionMQMessage.getMqTransactionId())) {
                    log.info("消息队列:Redis:事务消息:查询事务是否提交:事务ID:{},未提交事务,中止传递", transactionMQMessage.getMqTransactionId());
                    return;
                }
            }
            eventPublisher.publishEvent(mqMessage);
            mqMessageRedisTemplate.opsForStream().acknowledge(redisStreamProperty.getKey(), redisStreamProperty.getGroup(), message.getId());
        } catch (Exception e) {
            log.error("消息队列:Redis:出现异常:" + message.getId(), e);
        }
    }


    private Class<?> getMessageClass(String messageClazz) {
        try {
            return Class.forName(messageClazz);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
