package org.evolboot.mq.redis.producer;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ExtendRuntimeException;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.event.mq.MQMessage;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashMap;

/**
 * @author evol
 */
@Slf4j
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
        String _message = JsonUtil.stringify(message);
        data.put(message.getClass().getName(), _message);
        RecordId recordId = this.opsForStream().add(MapRecord.create(stream, data));
        if (recordId == null || ExtendObjects.isBlank(recordId.getValue())) {
            log.error("Redis:发送消息失败:{}", _message);
            throw new ExtendRuntimeException("Redis:发送消息失败");
        }
    }


}
