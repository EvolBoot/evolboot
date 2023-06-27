package org.evolboot.shared.event.mq;

/**
 * @author evol
 */
public class TestTransactionMQMessage extends TransactionMQMessage {
    @Override
    public Object getEventSourceId() {
        return 1L;
    }
}
