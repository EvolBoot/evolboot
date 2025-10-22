package org.evolboot.shared.event.pay;

import lombok.*;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.evolboot.shared.pay.PayoutOrderState;

import java.math.BigDecimal;

/**
 * 代付订单状态变更事件
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayoutOrderStateChangeMessage extends TransactionMQMessage<String> {

    private String payoutOrderId;

    private String foreignOrderId;

    private String internalOrderId;

    private BigDecimal payAmount;

    private PayoutOrderState payoutOrderState;

    @Override
    public String getEventSourceId() {
        return payoutOrderId;
    }
}
