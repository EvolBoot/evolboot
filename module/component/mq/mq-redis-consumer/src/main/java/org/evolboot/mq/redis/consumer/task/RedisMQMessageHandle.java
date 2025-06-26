package org.evolboot.mq.redis.consumer.task;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.mq.core.domain.mqtransaction.MqTransactionAppService;
import org.evolboot.mq.core.util.MqMessageUtil;
import org.evolboot.mq.redis.consumer.message.ExceptionMessageConvertDelayTimeMessage;
import org.evolboot.mq.redis.producer.MqMessageRedisTemplate;
import org.evolboot.mq.redis.producer.RedisMQMessagePublisher;
import org.evolboot.shared.event.mq.DelayMQMessage;
import org.evolboot.shared.event.mq.MQMessage;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StreamOperations;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * 自定义实现的回查
 *
 * @author evol
 */
@Slf4j
public class RedisMQMessageHandle implements Runnable {

    private final MqMessageRedisTemplate mqMessageRedisTemplate;
    private final MqTransactionAppService mqTransactionAppService;
    private final RedisMQMessagePublisher redisMQMessagePublisher;

    // 事务超时时间,如果在此时间内还没有查询到消息,则视为未提交事务
    private final static long TRANSACTION_TIMEOUT = 30 * 60;
    // 延时消息超时时间
    private final static long DELAY_TIME_TIMEOUT = 24 * 60 * 60;

    // 默认每次读取消息的数量
    private final int GET_MESSAGE_COUNT = 100;

    private final String key;
    private final String group;

    public RedisMQMessageHandle(MqMessageRedisTemplate mqMessageRedisTemplate, MqTransactionAppService mqTransactionAppService, RedisMQMessagePublisher redisMQMessagePublisher, String key, String group) {
        this.mqMessageRedisTemplate = mqMessageRedisTemplate;
        this.mqTransactionAppService = mqTransactionAppService;
        this.redisMQMessagePublisher = redisMQMessagePublisher;
        this.key = key;
        this.group = group;
    }


    public void handlePendingMessage() {
        StreamOperations<String, String, String> streamOperations = this.mqMessageRedisTemplate.opsForStream();
        PendingMessagesSummary pendingMessagesSummary = streamOperations.pending(key, group);
        // 消息的数量
        long totalPendingMessages = pendingMessagesSummary.getTotalPendingMessages();

        if (totalPendingMessages == 0) {
            log.debug("消息队列:Redis:{} 没有待处理的消息", key);
            return;
        }
        //每个消费者的pending消息数量
        Map<String, Long> pendingMessagesPerConsumer = pendingMessagesSummary.getPendingMessagesPerConsumer();
        for (Map.Entry<String, Long> entry : pendingMessagesPerConsumer.entrySet()) {
            String consumer = entry.getKey();
            Long value = entry.getValue();
            // 消费者
            // 消费者的pending消息数量
            long consumerTotalPendingMessages = value;

            log.info("消息队列:Redis:消费者:{}，一共有{}条pending消息,key:{},group:{}", consumer, consumerTotalPendingMessages, key, group);

            if (consumerTotalPendingMessages > 0) {
                // 读取消费者pending队列的记录，
                log.info("消息队列:Redis:消费者:pending start:{},{}", key, group);
                PendingMessages pendingMessages = streamOperations.pending(key, Consumer.from(group, consumer), Range.unbounded(), GET_MESSAGE_COUNT);
                log.info("消息队列:Redis:消费者:pending end:{},{}", key, group);
                // 遍历所有pending消息的详情
                for (PendingMessage pendingMessage : pendingMessages) {// 消息的ID
                    RecordId recordId = pendingMessage.getId();
                    // 消息从消费组中获取，到此刻的时间
                    Duration elapsedTimeSinceLastDelivery = pendingMessage.getElapsedTimeSinceLastDelivery();
                    // 消息被获取的次数
                    long deliveryCount = pendingMessage.getTotalDeliveryCount();
                    log.debug("消息队列:Redis:消息，id={}, elapsedTimeSinceLastDelivery={}, deliveryCount={}", recordId, elapsedTimeSinceLastDelivery, deliveryCount);
                    List<MapRecord<String, String, String>> mapRecords = streamOperations.range(key, Range.closed(recordId.getValue(), recordId.getValue()));
                    if (ExtendObjects.isEmpty(mapRecords)) {
                        log.info("消息队列:Redis:这个消息取不到:{}", recordId.getValue());
                    } else {
                        MapRecord<String, String, String> message = mapRecords.get(0);
                        String key = message.getValue().keySet().stream().findFirst().get();
                        Class<?> messageClass = MqMessageUtil.getMessageClass(key);
                        if (messageClass == null) {
                            log.error("消息队列:Redis：序列化:{},{},{},但消息类型为空:重构了?", key, message.getValue().get(key), messageClass);
                            continue;
                        }
                        MQMessage mqMessage = (MQMessage) JsonUtil.parse(message.getValue().get(key), messageClass);
                        if (mqMessage instanceof TransactionMQMessage transactionMQMessage && transactionMQMessage.getMqTransactionId() != null) {
                            handleTransactionMessage((TransactionMQMessage) mqMessage, recordId);
                        } else if (mqMessage instanceof DelayMQMessage) {
                            handleDelayTimeMessage((DelayMQMessage) mqMessage, recordId);
                        } else {
                            handleRealTimeMessage(mqMessage, recordId);
                        }
                    }
                }
            }
        }
        log.debug("消息队列:Redis:结束");
    }


    private void handleTransactionMessage(TransactionMQMessage mqMessage, RecordId recordId) {
        long messageCreateTimestamp = mqMessage.getMessageCreateTimestamp();
        long elapsedTimeSinceLastDeliverySeconds = (System.currentTimeMillis() - messageCreateTimestamp) / 1000;
        log.info("消息队列:Redis:开始处理事务消息:{}", mqMessage.getMqTransactionId());
        if (mqTransactionAppService.existsById(mqMessage.getMqTransactionId())) {
            log.info("消息队列:Redis:事务消息:{},{},消息回查,已查到,转为普通消息处理", mqMessage.getMqTransactionId(), recordId.getValue());
            mqMessage.setMqTransactionId(null);
            // 转为普通消息
            redisMQMessagePublisher.send(mqMessage);
            // 确认后会自动删除
            mqMessageRedisTemplate.opsForStream().acknowledge(key, group, recordId);
            mqMessageRedisTemplate.opsForStream().delete(key, recordId);
            log.info("消息队列:Redis:事务消息:确认并删除:{}", mqMessage.getMqTransactionId());
        } else if (elapsedTimeSinceLastDeliverySeconds >= TRANSACTION_TIMEOUT) {
            log.info("消息队列:Redis:事务消息:超过时间:{}, 删除", elapsedTimeSinceLastDeliverySeconds);
            // 确认后会自动删除
            mqMessageRedisTemplate.opsForStream().acknowledge(key, group, recordId);
            mqMessageRedisTemplate.opsForStream().delete(key, recordId);
        }

    }

    private void handleDelayTimeMessage(DelayMQMessage mqMessage, RecordId recordId) {

        long messageCreateTimestamp = mqMessage.getMessageCreateTimestamp();
        long elapsedTimeSinceLastDeliverySeconds = (System.currentTimeMillis() - messageCreateTimestamp) / 1000;

        if (elapsedTimeSinceLastDeliverySeconds >= mqMessage.getDelayTimeSeconds()) {
            log.debug("消息队列:Redis:延时消息:{},到了时间,转为普通消息处理", recordId.getValue());
            if (mqMessage instanceof ExceptionMessageConvertDelayTimeMessage) {
                ExceptionMessageConvertDelayTimeMessage exceptionMessageConvertDelayTimeMessage = (ExceptionMessageConvertDelayTimeMessage) mqMessage;
                MQMessage newMessage = (MQMessage) JsonUtil.parse(exceptionMessageConvertDelayTimeMessage.getJsonContent(), MqMessageUtil.getMessageClass(exceptionMessageConvertDelayTimeMessage.getClazz()));
                // 转为普通消息
                redisMQMessagePublisher.send(newMessage);
            } else {
                // 转为普通消息
                redisMQMessagePublisher.send(mqMessage);
            }

            // 确认后会自动删除
            mqMessageRedisTemplate.opsForStream().acknowledge(key, group, recordId);
            mqMessageRedisTemplate.opsForStream().delete(key, recordId);

        } else if (elapsedTimeSinceLastDeliverySeconds >= DELAY_TIME_TIMEOUT) {
            log.info("消息队列:Redis:延迟消息:{}, 超过时间:{}, 删除", recordId.getValue(), elapsedTimeSinceLastDeliverySeconds);
            mqMessageRedisTemplate.opsForStream().acknowledge(key, group, recordId);
            mqMessageRedisTemplate.opsForStream().delete(key, recordId);
        }
    }

    private void handleRealTimeMessage(MQMessage mqMessage, RecordId recordId) {
        log.info("消息队列:Redis:实时消息,重发:{}", recordId.getValue());
        // 转为普通消息
        redisMQMessagePublisher.send(mqMessage);
        // 确认消息
        mqMessageRedisTemplate.opsForStream().acknowledge(key, group, recordId);
        mqMessageRedisTemplate.opsForStream().delete(key, recordId);

    }


    @Override
    public void run() {
        try {
            handlePendingMessage();
        } catch (Exception e) {
            log.error("消息队列:Redis:定时任务出现错误", e);
        }

    }
}
