package org.evolboot.shared.event.pay;

import lombok.*;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.evolboot.shared.pay.ReceiptOrderState;

import java.math.BigDecimal;

/**
 * @author evol
 * @date 2021/11/19
 */

@Getter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ReceiptOrderStateChangeMessage extends TransactionMQMessage<String> {

    private String receiptOrderId;

    private String internalOrderId;

    private BigDecimal payAmount;

    private ReceiptOrderState receiptOrderState;

    @Override
    public String getEventSourceId() {
        return receiptOrderId;
    }

}
