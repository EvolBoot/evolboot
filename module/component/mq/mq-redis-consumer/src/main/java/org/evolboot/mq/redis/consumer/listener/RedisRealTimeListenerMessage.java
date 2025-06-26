package org.evolboot.mq.redis.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.mq.core.util.MqMessageUtil;
import org.evolboot.mq.redis.consumer.message.ExceptionMessageConvertDelayTimeMessage;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.evolboot.mq.redis.producer.RedisMQMessagePublisher;
import org.evolboot.mq.redis.producer.RedisStreamProperty;
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
    private final RedisMQMessagePublisher redisMQMessagePublisher;

    public RedisRealTimeListenerMessage(RedisStreamProperty redisStreamProperty, EventPublisher eventPublisher, MqMessageRedisTemplate mqMessageRedisTemplate, RedisMQMessagePublisher redisMQMessagePublisher) {
        this.redisStreamProperty = redisStreamProperty;
        this.eventPublisher = eventPublisher;
        this.mqMessageRedisTemplate = mqMessageRedisTemplate;
        this.redisMQMessagePublisher = redisMQMessagePublisher;
    }

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        try {
            long startTime = System.currentTimeMillis();
            String key = message.getValue().keySet().stream().findFirst().get();
            Class<?> messageClass = MqMessageUtil.getMessageClass(key);
            log.info("消息队列:实时消息:收到消息:{},{}", message.getId(), messageClass);
            MQMessage mqMessage = (MQMessage) JsonUtil.parse(message.getValue().get(key), messageClass);
            eventPublisher.publishEvent(mqMessage);
            long processTime = System.currentTimeMillis() - startTime;
            log.info("消息队列:实时消息:处理时长:{},时长:{} ms,{}", message.getId(), processTime, messageClass);
            if (processTime > 60000) {
                log.error("消息队列:实时消息:处理时长超过一分钟的:{}", messageClass);
            }
            mqMessageRedisTemplate.opsForStream().acknowledge(redisStreamProperty.getKeyForRealTime(), redisStreamProperty.getGroup(), message.getId());
            mqMessageRedisTemplate.opsForStream().delete(redisStreamProperty.getKeyForRealTime(), message.getId());
        } catch (Exception e) {
            log.error("消息队列:实时消息:出现异常:改为延时消息:" + message.getId(), e);
            String key = message.getValue().keySet().stream().findFirst().get();
            MQMessage mqMessage = (MQMessage) JsonUtil.parse(message.getValue().get(key), MqMessageUtil.getMessageClass(key));

            ExceptionMessageConvertDelayTimeMessage exceptionMessageConvertDelayTimeMessage = new ExceptionMessageConvertDelayTimeMessage(key, message.getValue().get(key));
            exceptionMessageConvertDelayTimeMessage.setMessageCreateTimestamp(mqMessage.getMessageCreateTimestamp());
            redisMQMessagePublisher.sendDelayTimeSeconds(exceptionMessageConvertDelayTimeMessage, 60);

            mqMessageRedisTemplate.opsForStream().acknowledge(redisStreamProperty.getKeyForRealTime(), redisStreamProperty.getGroup(), message.getId());
            mqMessageRedisTemplate.opsForStream().delete(redisStreamProperty.getKeyForRealTime(), message.getId());

        }
    }


}
