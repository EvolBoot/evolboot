package org.evolboot.shared.event.pay;

import lombok.*;
import org.evolboot.shared.event.mq.TransactionMQMessage;

/**
 * 代付订单创建事件
 */
@Getter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class PayoutOrderCreatedMessage extends TransactionMQMessage<String> {

    private String payoutOrderId;

    @Override
    public String getEventSourceId() {
        return payoutOrderId;
    }
}
