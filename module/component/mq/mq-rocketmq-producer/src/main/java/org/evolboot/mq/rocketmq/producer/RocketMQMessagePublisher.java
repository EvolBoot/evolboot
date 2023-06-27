package org.evolboot.mq.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.evolboot.core.mq.DelayLevel;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.mq.core.domain.mqtransaction.MqTransactionAppService;
import org.evolboot.shared.event.mq.MQMessage;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class RocketMQMessagePublisher implements MQMessagePublisher {

    @Value("${rocketmq.producer.topic}")
    private String topic;

    private final RocketMQTemplate rocketMQTemplate;

    private final MqTransactionAppService mqTransactionAppService;

    public RocketMQMessagePublisher(RocketMQTemplate rocketMQTemplate, MqTransactionAppService mqTransactionAppService) {
        this.rocketMQTemplate = rocketMQTemplate;
        this.mqTransactionAppService = mqTransactionAppService;
    }

    @Override
    public <T extends TransactionMQMessage> void sendMessageInTransaction(T message) {
        if (message.getMessageCreateTimestamp() == null) {
            message.setMessageCreateTimestamp(System.currentTimeMillis());
        }
        if (message.getMqTransactionId() == null) {
            message.setMqTransactionId(mqTransactionAppService.create().id());
        }
        String tag = message.getClass().getName();
        Message<T> _message = MessageBuilder
                .withPayload(message)
                .setHeader(MqConstant.TAG, tag)
                .build();
        TransactionSendResult transactionSendResult =
                rocketMQTemplate.sendMessageInTransaction(buildDestination(topic, tag), _message, message.getEventSourceId());
        String msgId = transactionSendResult.getMsgId();
        log.info("发送事务消息:消息ID: {}, Source: {}, Tag: {}", msgId, message.getEventSourceId(), tag);

    }

    @Override
    public <T extends MQMessage> void send(T message) {
        if (message.getMessageCreateTimestamp() == null) {
            message.setMessageCreateTimestamp(System.currentTimeMillis());
        }
        String tag = message.getClass().getName();
        Message<T> _message = MessageBuilder
                .withPayload(message)
                .setHeader(MqConstant.TAG, tag)
                .build();
        rocketMQTemplate.syncSend(buildDestination(topic, tag), _message);

    }

    /**
     * 延时消息
     *
     * @param message    消息
     * @param delayLevel 延时级别
     * @param <T>
     */
    @Override
    public <T extends MQMessage> void send(T message, DelayLevel delayLevel) {
        if (message.getMessageCreateTimestamp() == null) {
            message.setMessageCreateTimestamp(System.currentTimeMillis());
        }
        String tag = message.getClass().getName();
        Message<T> _message = MessageBuilder
                .withPayload(message)
                .setHeader(MqConstant.TAG, tag)
                .build();
        rocketMQTemplate.syncSendDelayTimeSeconds(buildDestination(topic, tag), _message, delayLevel.getDelayTimeSeconds());

    }

    /**
     * 延时消息
     *
     * @param message   消息
     * @param delayTime 延时级别
     * @param <T>
     */
    @Override
    public <T extends MQMessage> void sendDelayTimeSeconds(T message, long delayTime) {
        if (message.getMessageCreateTimestamp() == null) {
            message.setMessageCreateTimestamp(System.currentTimeMillis());
        }
        String tag = message.getClass().getName();
        Message<T> _message = MessageBuilder
                .withPayload(message)
                .setHeader(MqConstant.TAG, tag)
                .build();
        rocketMQTemplate.syncSendDelayTimeSeconds(buildDestination(topic, tag), _message, delayTime);

    }

    private String buildDestination(String topic, String tag) {
        return topic + ":" + tag;
    }

}
