package org.evolboot.core.mq;

import org.evolboot.core.exception.ExtendRuntimeException;
import org.evolboot.shared.event.mq.MQMessage;
import org.evolboot.shared.event.mq.TransactionMQMessage;


/**
 * 消息队列,消息发送器
 *
 * @author evol
 */
public interface MQMessagePublisher {


    /**
     * 事务消息
     *
     * @param message
     * @param <T>
     * @return
     */
    default <T extends TransactionMQMessage> void sendMessageInTransaction(T message) {
        throw new ExtendRuntimeException("暂不支持事务消息");
    }

    /**
     * 实时消息
     *
     * @param message
     * @param <T>
     */
    default <T extends MQMessage> void send(T message) {
        throw new ExtendRuntimeException("暂不支持发送消息");
    }

    /**
     * 延时消息
     *
     * @param message    消息
     * @param delayLevel 延时级别
     * @param <T>
     */
    default <T extends MQMessage> void send(T message, DelayLevel delayLevel) {
        throw new ExtendRuntimeException("暂不支持延迟消息");
    }

    /**
     * 延时消息
     *
     * @param message   消息
     * @param delayTime 延时级别
     * @param <T>
     */
    default <T extends MQMessage> void sendDelayTimeSeconds(T message, long delayTime) {
        throw new ExtendRuntimeException("暂不支持延时消息");
    }


}
