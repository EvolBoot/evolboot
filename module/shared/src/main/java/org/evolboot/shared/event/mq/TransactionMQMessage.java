package org.evolboot.shared.event.mq;


/**
 * @author evol
 * 
 */
public abstract class TransactionMQMessage<T> implements RocketMQMessage<T> {

    private Long mqTransactionId;

    private long messageCreateTimestamp;

    public void setMqTransactionId(Long mqTransactionId) {
        this.mqTransactionId = mqTransactionId;
    }

    public TransactionMQMessage() {
        this.messageCreateTimestamp = System.currentTimeMillis();
    }

    public Long getMqTransactionId() {
        return mqTransactionId;
    }

    public long getMessageCreateTimestamp() {
        return messageCreateTimestamp;
    }

    public void setMessageCreateTimestamp(long messageCreateTimestamp) {
        this.messageCreateTimestamp = messageCreateTimestamp;
    }
}
