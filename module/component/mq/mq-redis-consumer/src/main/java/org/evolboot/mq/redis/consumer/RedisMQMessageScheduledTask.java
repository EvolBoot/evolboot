package org.evolboot.mq.redis.consumer;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.mq.redis.consumer.autoconfigure.RedisStreamProperty;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author linjie
 */
@Service
@Slf4j
public class RedisMQMessageScheduledTask implements Runnable {

    private final RedisStreamProperty redisStreamProperty;
    private final MqMessageRedisTemplate mqMessageRedisTemplate;
    private final RedisListenerMessage listenerMessage;
    private final ScheduledExecutorService executorService;

    private long TIMEOUT = 24 * 60 * 60;

    public RedisMQMessageScheduledTask(RedisStreamProperty redisStreamProperty, MqMessageRedisTemplate mqMessageRedisTemplate, RedisListenerMessage listenerMessage) {
        this.redisStreamProperty = redisStreamProperty;
        this.mqMessageRedisTemplate = mqMessageRedisTemplate;
        this.listenerMessage = listenerMessage;
        // 定时执行,5秒
        this.executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(this, 5, 5, TimeUnit.SECONDS);
    }


    public void handlePendingMessage() {
        StreamOperations<String, String, String> streamOperations = this.mqMessageRedisTemplate.opsForStream();
        PendingMessagesSummary pendingMessagesSummary = streamOperations.pending(redisStreamProperty.getKey(), redisStreamProperty.getGroup());
        // 消息的数量
        long totalPendingMessages = pendingMessagesSummary.getTotalPendingMessages();

        if (totalPendingMessages == 0) {
            log.info("消息队列:Redis:没有待处理的消息");
            return;
        }

        // 消费组名称
        String groupName = pendingMessagesSummary.getGroupName();

        // pending队列中的最小ID
        String minMessageId = pendingMessagesSummary.minMessageId();

        // pending队列中的最大ID
        String maxMessageId = pendingMessagesSummary.maxMessageId();

        log.info("消息队列:Redis:{}，一共有{}条pending消息，最大ID={}，最小ID={}", groupName, totalPendingMessages, minMessageId, maxMessageId);
        //每个消费者的pending消息数量
        Map<String, Long> pendingMessagesPerConsumer = pendingMessagesSummary.getPendingMessagesPerConsumer();
        pendingMessagesPerConsumer.entrySet().forEach(entry -> {

            // 消费者
            String consumer = entry.getKey();
            // 消费者的pending消息数量
            long consumerTotalPendingMessages = entry.getValue();

            log.info("消息队列:Redis:消费者:{}，一共有{}条pending消息", consumer, consumerTotalPendingMessages);

            if (consumerTotalPendingMessages > 0) {
                // 读取消费者pending队列的记录，
                PendingMessages pendingMessages = streamOperations.pending(redisStreamProperty.getKey(), Consumer.from(redisStreamProperty.getGroup(), consumer));
                // 遍历所有pending消息的详情
                pendingMessages.forEach(message -> {
                    // 消息的ID
                    RecordId recordId = message.getId();
                    // 消息从消费组中获取，到此刻的时间
                    Duration elapsedTimeSinceLastDelivery = message.getElapsedTimeSinceLastDelivery();
                    // 如果距离时间太久了,就不重试了，暂定24小时吧
                    if (elapsedTimeSinceLastDelivery.getSeconds() >= TIMEOUT) {
                        log.info("消息超时了:{},超过:{} 秒了,就不处理了", message.getId(), elapsedTimeSinceLastDelivery.getSeconds());
                        streamOperations.acknowledge(redisStreamProperty.getKey(), redisStreamProperty.getGroup(), recordId);
                    } else {
                        // 消息被获取的次数
                        long deliveryCount = message.getTotalDeliveryCount();
                        log.info("消息队列:Redis:消息，id={}, elapsedTimeSinceLastDelivery={}, deliveryCount={}", recordId, elapsedTimeSinceLastDelivery, deliveryCount);
                        List<MapRecord<String, String, String>> mapRecords = streamOperations.range(redisStreamProperty.getKey(), Range.closed(recordId.getValue(), recordId.getValue()));
                        if (ExtendObjects.isEmpty(mapRecords)) {
                            log.info("消息队列:Redis:这个消息取不到:{}", recordId.getValue());
                        } else {
                            MapRecord<String, String, String> record = mapRecords.get(0);
                            // 这里执行日志输出，模拟的就是消费逻辑
                            listenerMessage.onMessage(record);
                            log.info("消费了pending消息:id={}, value={}", record.getId(), record.getValue());
                        }
                    }
                });
            }
        });
        log.info("消息队列:Redis:结束");
    }

    @Override
    public void run() {
        handlePendingMessage();
    }
}
