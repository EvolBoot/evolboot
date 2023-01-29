package org.evolboot.mq.producer;

import org.evolboot.shared.event.mq.RocketMQMessage;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;

/**
 * 事务检查器
 *
 * @author evol
 * 
 */
public interface RocketMQLocalTransactionCheck<T extends RocketMQMessage> {


    /**
     * 检查事务是否提交
     *
     * @param message
     * @param checkTimes
     * @return
     */
    RocketMQLocalTransactionState checkLocalTransaction(final T message, int checkTimes);


}
