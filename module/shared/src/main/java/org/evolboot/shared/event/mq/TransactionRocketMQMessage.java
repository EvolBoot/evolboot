package org.evolboot.shared.event.mq;


/**
 * @author evol
 * 
 */
public abstract class TransactionRocketMQMessage<T> implements RocketMQMessage<T> {

    private Long mqTransactionId;

    public void setMqTransactionId(Long mqTransactionId) {
        this.mqTransactionId = mqTransactionId;
    }

    public Long getMqTransactionId() {
        return mqTransactionId;
    }
}
