package org.evolboot.mq.redis.producer.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.evolboot.shared.event.mq.MQMessage;
import org.springframework.beans.factory.annotation.Value;
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

/**
 * @author evol
 */
@Configuration
@Slf4j
public class RedisProducerMqConfiguration {

    @Value("${spring.redis.mq-stream.key}")
    private String key;


    @Value("${spring.redis.mq-stream.group}")
    private String group;


    @Bean
    public MqMessageRedisTemplate getMqMessageRedisTemplate(RedisConnectionFactory connectionFactory) {
        return new MqMessageRedisTemplate(connectionFactory);
    }



}
