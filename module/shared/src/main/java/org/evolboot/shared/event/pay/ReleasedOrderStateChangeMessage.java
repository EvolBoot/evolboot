package org.evolboot.shared.event.pay;

import lombok.*;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.evolboot.shared.pay.ReleasedOrderState;

import java.math.BigDecimal;

/**
 * @author evol
 * @date 2021/12/10
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReleasedOrderStateChangeMessage extends TransactionMQMessage<String> {

    private String releasedOrderId;

    private String foreignOrderId;

    private String internalOrderId;

    private BigDecimal payAmount;

    private ReleasedOrderState releasedOrderState;


    @Override
    public String getEventSourceId() {
        return releasedOrderId;
    }
}
