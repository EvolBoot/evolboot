package org.evolboot.mq.redis.consumer.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.redis.consumer.listener.RedisDelayTimeListenerMessage;
import org.evolboot.mq.redis.consumer.listener.RedisRealTimeListenerMessage;
import org.evolboot.mq.redis.consumer.listener.RedisTransactionListenerMessage;
import org.evolboot.mq.redis.consumer.task.RedisMQMessageScheduledTask;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.evolboot.mq.redis.producer.RedisStreamProperty;
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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;

/**
 * Redis MQ 里面有两种定时器（线程）
 * 一种是 StreamMessageListenerContainer 自带的，配置 pollTimeout(Duration.ofSeconds(1)) 拉取信息，
 * 一种是自己实现的，用来简单处理 延时消息和事务消息。
 * 每个消息（实时消息，延时消息，事务消息）都有自己的队列
 * 当收到实时消息时，处理，如果抛出了异常，则转为延时消息，通过自定义的线程去定时拉取，然后转为实时消息继续处理。
 * 当收到事务消息时，判断是否事务是否结束（通过数据库中的事务ID），如果结束，则转为实时消息
 * 当收到延时消息时，判断时间是否到了，如果到了转为实时消息，如果没到，则不管。
 *
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
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  // 核心线程数
        executor.setMaxPoolSize(10);   // 最大线程数
        executor.setQueueCapacity(50); // 任务队列容量
        executor.setThreadNamePrefix("stream-listener-");
        executor.initialize();

        return StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                // 可以理解为 Stream Key 的序列化方式
                .keySerializer(RedisSerializer.string())
                // 一次最多获取多少条消息
                .batchSize(5)
                .executor(executor)
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
