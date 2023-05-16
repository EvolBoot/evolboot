package org.evolboot.shared.event.mq;


import lombok.Getter;
import lombok.Setter;

/**
 * 事务消息
 *
 * @author evol
 */
@Setter
@Getter
public abstract class TransactionMQMessage<T> extends RocketMQMessage<T> {

    /**
     * 事务ID,不用手动
     */
    private Long mqTransactionId;


}
