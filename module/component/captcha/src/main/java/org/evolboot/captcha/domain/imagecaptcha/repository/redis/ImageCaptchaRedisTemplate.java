package org.evolboot.captcha.domain.imagecaptcha.repository.redis;

import org.evolboot.captcha.domain.imagecaptcha.ImageCaptcha;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author evol
 */
public class ImageCaptchaRedisTemplate extends RedisTemplate<String, ImageCaptcha> {

    public ImageCaptchaRedisTemplate() {
        setKeySerializer(RedisSerializer.string());
        setValueSerializer(new Jackson2JsonRedisSerializer(ImageCaptcha.class));
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(RedisSerializer.string());
    }

    public ImageCaptchaRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
