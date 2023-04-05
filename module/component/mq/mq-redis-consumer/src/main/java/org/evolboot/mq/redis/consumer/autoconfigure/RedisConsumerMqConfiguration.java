package org.evolboot.mq.redis.consumer.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.redis.consumer.task.RedisMQMessageScheduledTask;
import org.evolboot.mq.redis.consumer.listener.RedisDelayTimeListenerMessage;
import org.evolboot.mq.redis.consumer.listener.RedisRealTimeListenerMessage;
import org.evolboot.mq.redis.consumer.listener.RedisTransactionListenerMessage;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.evolboot.mq.redis.producer.RedisStreamProperty;
import org.evolboot.shared.event.mq.MQMessage;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import javax.annotation.PreDestroy;
import java.time.Duration;

/**
 * @author evol
 */
@Configuration
@Slf4j
public class RedisConsumerMqConfiguration implements DisposableBean {


    private final RedisStreamProperty redisStreamProperty;
    private StreamMessageListenerContainer streamMessageListenerContainer;

    public RedisConsumerMqConfiguration(RedisStreamProperty redisStreamProperty) {
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
                .batchSize(50)
                .build();
    }


    @Bean
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer(
            RedisConnectionFactory factory,
            StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> streamMessageListenerContainerOptions,
            MqMessageRedisTemplate mqMessageRedisTemplate,
            RedisTransactionListenerMessage redisTransactionListenerMessage,
            RedisRealTimeListenerMessage redisRealTimeListenerMessage,
            RedisDelayTimeListenerMessage redisDelayTimeListenerMessage,
            RedisMQMessageScheduledTask redisMQMessageScheduledTask
    ) {

        /**
         * 实时消息队列
         */
        if (Boolean.FALSE.equals(mqMessageRedisTemplate.hasKey(redisStreamProperty.getKeyForRealTime()))) {
            mqMessageRedisTemplate.createGroup(redisStreamProperty.getKeyForRealTime(), redisStreamProperty.getGroup());
        }

        /**
         * 事务消息队列
         */
        if (Boolean.FALSE.equals(mqMessageRedisTemplate.hasKey(redisStreamProperty.getKeyForTransaction()))) {
            mqMessageRedisTemplate.createGroup(redisStreamProperty.getKeyForTransaction(), redisStreamProperty.getGroup());
        }

        /**
         * 延时消息队列
         */
        if (Boolean.FALSE.equals(mqMessageRedisTemplate.hasKey(redisStreamProperty.getKeyForDelayTime()))) {
            mqMessageRedisTemplate.createGroup(redisStreamProperty.getKeyForDelayTime(), redisStreamProperty.getGroup());
        }

        StreamMessageListenerContainer listenerContainer = StreamMessageListenerContainer.create(factory,
                streamMessageListenerContainerOptions);
        // 实时队列线程数给多一点
        log.info("消息队列:Redis:实时监听线程数:{}", redisStreamProperty.getThreadNumber());

        for (int i = 0; i < redisStreamProperty.getThreadNumber(); i++) {
            listenerContainer.receive(Consumer.from(redisStreamProperty.getGroup(), redisStreamProperty.getConsumer()),
                    StreamOffset.create(redisStreamProperty.getKeyForRealTime(), ReadOffset.lastConsumed()),
                    redisRealTimeListenerMessage);
        }

        /**
         * 延时消息处理
         */
        listenerContainer.receive(Consumer.from(redisStreamProperty.getGroup(), redisStreamProperty.getConsumer()),
                StreamOffset.create(redisStreamProperty.getKeyForDelayTime(), ReadOffset.lastConsumed()),
                redisDelayTimeListenerMessage);

        /**
         * 事务消息处理
         */
        listenerContainer.receive(Consumer.from(redisStreamProperty.getGroup(), redisStreamProperty.getConsumer()),
                StreamOffset.create(redisStreamProperty.getKeyForTransaction(), ReadOffset.lastConsumed()),
                redisTransactionListenerMessage);


        listenerContainer.start();

        // 定时任务
        redisMQMessageScheduledTask.init();

        this.streamMessageListenerContainer = listenerContainer;
        return listenerContainer;
    }


    @Override
    public void destroy() throws Exception {
        log.info("消息队列:Redis:开始停止");
        this.streamMessageListenerContainer.stop();
        log.info("消息队列:Redis:已停止");
    }
}
