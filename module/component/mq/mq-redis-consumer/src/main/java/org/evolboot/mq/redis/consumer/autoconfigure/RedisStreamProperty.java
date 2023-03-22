package org.evolboot.mq.redis.consumer.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author evol
 */
@Configuration
@Getter
@Setter
public class RedisStreamProperty {

    @Value("${spring.redis.mq-stream.key}")
    private String key;

    @Value("${spring.redis.mq-stream.group}")
    private String group;


    @Value("${spring.redis.mq-stream.consumer}")
    private String consumer;


}
