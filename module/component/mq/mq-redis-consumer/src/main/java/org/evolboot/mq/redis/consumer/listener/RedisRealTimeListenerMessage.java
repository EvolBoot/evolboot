package org.evolboot.mq.redis.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.mq.core.domain.mqtransaction.MqTransactionAppService;
import org.evolboot.mq.core.util.MqMessageUtil;
import org.evolboot.mq.redis.consumer.message.ExceptionMessageConvertDelayTimeMessage;
import org.evolboot.mq.redis.producer.RedisStreamProperty;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.evolboot.mq.redis.producer.RedisMQMessagePublisher;
import org.evolboot.shared.event.mq.MQMessage;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evol
 */
@Component
@Slf4j
public class RedisRealTimeListenerMessage implements StreamListener<String, MapRecord<String, String, String>> {

    private final RedisStreamProperty redisStreamProperty;
    private final EventPublisher eventPublisher;
    private final MqMessageRedisTemplate mqMessageRedisTemplate;
    private final MqTransactionAppService mqTransactionAppService;
    private long TIMEOUT_TRANSACTION_SECONDS = 60 * 60;
    private final RedisMQMessagePublisher redisMQMessagePublisher;

    public RedisRealTimeListenerMessage(RedisStreamProperty redisStreamProperty, EventPublisher eventPublisher, MqMessageRedisTemplate mqMessageRedisTemplate, MqTransactionAppService mqTransactionAppService, RedisMQMessagePublisher redisMQMessagePublisher) {
        this.redisStreamProperty = redisStreamProperty;
        this.eventPublisher = eventPublisher;
        this.mqMessageRedisTemplate = mqMessageRedisTemplate;
        this.mqTransactionAppService = mqTransactionAppService;
        this.redisMQMessagePublisher = redisMQMessagePublisher;
    }

    @Override
    @Transactional
    public void onMessage(MapRecord<String, String, String> message) {

        try {
            String key = message.getValue().keySet().stream().findFirst().get();
            log.info("消息队列:Redis:收到消息:{},{},{},{},线程ID:{}", message.getStream(), message.getId(), key, message.getValue().get(key), Thread.currentThread().getId());
            MQMessage mqMessage = (MQMessage) JsonUtil.parse(message.getValue().get(key), MqMessageUtil.getMessageClass(key));
            eventPublisher.publishEvent(mqMessage);
            mqMessageRedisTemplate.opsForStream().acknowledge(redisStreamProperty.getKeyForRealTime(), redisStreamProperty.getGroup(), message.getId());
        } catch (Exception e) {
            log.error("消息队列:Redis:出现异常:改为延时消息" + message.getId(), e);
            String key = message.getValue().keySet().stream().findFirst().get();
            MQMessage mqMessage = (MQMessage) JsonUtil.parse(message.getValue().get(key), MqMessageUtil.getMessageClass(key));

            ExceptionMessageConvertDelayTimeMessage exceptionMessageConvertDelayTimeMessage = new ExceptionMessageConvertDelayTimeMessage(key, message.getValue().get(key));
            exceptionMessageConvertDelayTimeMessage.setMessageCreateTimestamp(mqMessage.getMessageCreateTimestamp());
            redisMQMessagePublisher.sendDelayTimeSeconds(exceptionMessageConvertDelayTimeMessage, 60);

            mqMessageRedisTemplate.opsForStream().acknowledge(redisStreamProperty.getKeyForRealTime(), redisStreamProperty.getGroup(), message.getId());


        }
    }


}
