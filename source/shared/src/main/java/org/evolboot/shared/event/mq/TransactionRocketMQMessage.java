package org.evolboot.shared.event.mq;


/**
 * @author evol
 * 
 */
public interface TransactionRocketMQMessage<T> extends RocketMQMessage<T> {

    Long getMqTransactionId();

}
