package org.evolboot.captcha.domain.mobilecaptcha.repository.redis;

import org.evolboot.captcha.domain.mobilecaptcha.entity.MobileCaptcha;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author evol
 */
public class MobileCaptchaRedisTemplate extends RedisTemplate<String, MobileCaptcha> {

    public MobileCaptchaRedisTemplate() {
        setKeySerializer(RedisSerializer.string());
        setValueSerializer(new Jackson2JsonRedisSerializer(MobileCaptcha.class));
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(RedisSerializer.string());
    }

    public MobileCaptchaRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
