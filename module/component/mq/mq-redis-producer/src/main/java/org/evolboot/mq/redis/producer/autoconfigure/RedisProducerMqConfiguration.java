package org.evolboot.mq.redis.producer.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author evol
 */
@Configuration
@Slf4j
public class RedisProducerMqConfiguration {


    @Bean
    public MqMessageRedisTemplate getMqMessageRedisTemplate(RedisConnectionFactory connectionFactory) {
        return new MqMessageRedisTemplate(connectionFactory);
    }


}
