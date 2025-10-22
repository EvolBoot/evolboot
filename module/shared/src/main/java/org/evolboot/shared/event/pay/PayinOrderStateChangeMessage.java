package org.evolboot.shared.event.pay;

import lombok.*;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.evolboot.shared.pay.PayinOrderState;

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
public class PayinOrderStateChangeMessage extends TransactionMQMessage<String> {

    private String payinOrderId;

    private String internalOrderId;

    private BigDecimal payAmount;

    private PayinOrderState payinOrderState;

    @Override
    public String getEventSourceId() {
        return payinOrderId;
    }

}
