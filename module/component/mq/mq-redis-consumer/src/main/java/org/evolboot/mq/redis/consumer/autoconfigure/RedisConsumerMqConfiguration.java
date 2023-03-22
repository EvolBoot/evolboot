package org.evolboot.mq.redis.consumer.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.redis.consumer.RedisListenerMessage;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.evolboot.shared.event.mq.MQMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author evol
 */
@Configuration
@Slf4j
public class RedisConsumerMqConfiguration {


    private final MqMessageRedisTemplate mqMessageRedisTemplate;
    private final RedisStreamProperty redisStreamProperty;

    public RedisConsumerMqConfiguration(MqMessageRedisTemplate mqMessageRedisTemplate, RedisStreamProperty redisStreamProperty) {
        this.mqMessageRedisTemplate = mqMessageRedisTemplate;
        this.redisStreamProperty = redisStreamProperty;
    }





    @Bean
    public StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ?> streamMessageListenerContainerOptions() {
        return StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                // 可以理解为 Stream Key 的序列化方式
                .keySerializer(RedisSerializer.string())
                // 一次最多获取多少条消息
                .batchSize(10)
                .build();
    }

    @Bean
    public <T extends MQMessage<?>> StreamMessageListenerContainer<String, MapRecord<String, String, T>> streamMessageListenerContainer(RedisConnectionFactory factory, StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, T>> streamMessageListenerContainerOptions, MqMessageRedisTemplate mqMessageRedisTemplate) {
        if (!mqMessageRedisTemplate.hasKey(redisStreamProperty.getKey())) {
            mqMessageRedisTemplate.createGroup(redisStreamProperty.getKey(), redisStreamProperty.getGroup());
        }
        StreamMessageListenerContainer listenerContainer = StreamMessageListenerContainer.create(factory,
                streamMessageListenerContainerOptions);
        listenerContainer.start();
        return listenerContainer;
    }


    @Bean
    public Subscription subscription(StreamMessageListenerContainer streamMessageListenerContainer, RedisListenerMessage redisListenerMessage) {
        Subscription subscription = streamMessageListenerContainer.receive(
                Consumer.from(redisStreamProperty.getGroup(), redisStreamProperty.getConsumer()),
                StreamOffset.create(redisStreamProperty.getKey(), ReadOffset.lastConsumed()),
                redisListenerMessage
        );
        return subscription;
    }


}
