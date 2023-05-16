package org.evolboot.shared.event.pay;

import lombok.*;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.evolboot.shared.pay.ReleasedOrderStatus;

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
public class ReleasedOrderStatusChangeMessage extends TransactionMQMessage<String> {

    private String releasedOrderId;

    private String foreignOrderId;

    private String internalOrderId;

    private BigDecimal payAmount;

    private ReleasedOrderStatus releasedOrderStatus;


    @Override
    public String getSource() {
        return releasedOrderId;
    }
}
