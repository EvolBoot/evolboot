package org.evolboot.mq.producer;

import org.evolboot.shared.event.mq.RocketMQMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author evol
 * 
 */
@Component
@Slf4j
public class RocketMQMessagePublisher {

    @Value("${rocketmq.producer.topic}")
    private String topic;

    private final RocketMQTemplate rocketMQTemplate;

    public RocketMQMessagePublisher(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public <T extends RocketMQMessage> TransactionSendResult sendMessageInTransaction(T message) {
        String tag = message.getClass().getName();
        Message<T> _message = MessageBuilder
                .withPayload(message)
                .setHeader(MqConstant.TAG, tag)
                .build();
        TransactionSendResult transactionSendResult =
                rocketMQTemplate.sendMessageInTransaction(topic + ":" + tag, _message, message.getSource());
        String msgId = transactionSendResult.getMsgId();
        log.info("发送事务消息:消息ID: {}, Source: {}, Tag: {}", msgId, message.getSource(), tag);
        return transactionSendResult;

    }

    public <T extends RocketMQMessage> void send(T message) {
        String tag = message.getClass().getName();
        Message<T> _message = MessageBuilder
                .withPayload(message)
                .setHeader(MqConstant.TAG, tag)
                .build();
        rocketMQTemplate.syncSend(topic + ":" + tag, _message);

    }

    /**
     * 延时消息
     *
     * @param message    消息
     * @param delayLevel 延时级别
     * @param <T>
     */
    public <T extends RocketMQMessage> void send(T message, DelayLevel delayLevel) {
        String tag = message.getClass().getName();
        Message<T> _message = MessageBuilder
                .withPayload(message)
                .setHeader(MqConstant.TAG, tag)
                .build();
        rocketMQTemplate.syncSend(topic + ":" + tag, _message, 3000L, delayLevel.getDelayLevel());

    }
}
