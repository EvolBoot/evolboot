package org.evolboot.mq.redis.producer;

import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.event.mq.MQMessage;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashMap;

/**
 * @author evol
 */
public class MqMessageRedisTemplate extends RedisTemplate<String, String> {

    public MqMessageRedisTemplate() {
        setKeySerializer(RedisSerializer.string());
        setValueSerializer(RedisSerializer.string());
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(RedisSerializer.string());
    }

    public MqMessageRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

    /**
     * 创建消费组
     *
     * @param key
     * @param group
     * @return
     */
    public String createGroup(String key, String group) {
        return this.opsForStream().createGroup(key, group);
    }

    public <T extends MQMessage<?>> void addMessage(String stream, T message) {
        HashMap<String, String> data = new HashMap<>(1);
        data.put(message.getClass().getName(), JsonUtil.stringify(message));
        this.opsForStream().add(MapRecord.create(stream, data));
    }


}
