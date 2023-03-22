package org.evolboot.core.mq;

import org.evolboot.shared.event.mq.MQMessage;
import org.evolboot.shared.event.mq.TransactionMQMessage;


/**
 * 消息队列,消息发送器
 * @author evol
 */
public interface MQMessagePublisher {


    /**
     * 事务消息
     * @param message
     * @param <T>
     * @return
     */
    <T extends TransactionMQMessage> void sendMessageInTransaction(T message);

    /**
     * 实时消息
     * @param message
     * @param <T>
     */
    <T extends MQMessage> void send(T message);

    /**
     * 延时消息
     *
     * @param message    消息
     * @param delayLevel 延时级别
     * @param <T>
     */
    <T extends MQMessage> void send(T message, DelayLevel delayLevel);

    /**
     * 延时消息
     *
     * @param message   消息
     * @param delayTime 延时级别
     * @param <T>
     */
    <T extends MQMessage> void sendDelayTimeSeconds(T message, long delayTime);


}
