package org.evolboot.shared.event.pay;

import lombok.*;
import org.evolboot.shared.event.mq.TransactionRocketMQMessage;
import org.evolboot.shared.pay.ReceiptOrderStatus;

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
public class ReceiptOrderStatusChangeMessage extends TransactionRocketMQMessage<String> {

    private String receiptOrderId;

    private String internalOrderId;

    private BigDecimal payAmount;

    private ReceiptOrderStatus receiptOrderStatus;

    @Override
    public String getSource() {
        return receiptOrderId;
    }

}
