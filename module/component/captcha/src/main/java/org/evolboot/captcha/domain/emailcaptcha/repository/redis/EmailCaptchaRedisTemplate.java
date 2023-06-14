package org.evolboot.captcha.domain.emailcaptcha.repository.redis;

import org.evolboot.captcha.autoconfigure.EmailCaptchaProperties;
import org.evolboot.captcha.domain.emailcaptcha.entity.EmailCaptcha;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import static org.evolboot.captcha.autoconfigure.EmailCaptchaProperties.CONDITIONAL_ON_PROPERTY_TYPE;

/**
 * @author evol
 */
public class EmailCaptchaRedisTemplate extends RedisTemplate<String, EmailCaptcha> {

    public EmailCaptchaRedisTemplate() {
        setKeySerializer(RedisSerializer.string());
        setValueSerializer(new Jackson2JsonRedisSerializer(EmailCaptcha.class));
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(RedisSerializer.string());
    }

    public EmailCaptchaRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }


    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
