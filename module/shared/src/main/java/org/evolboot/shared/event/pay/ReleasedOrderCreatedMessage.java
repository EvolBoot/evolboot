package org.evolboot.shared.event.pay;

import lombok.*;
import org.evolboot.shared.event.mq.TransactionMQMessage;

/**
 * @author evol
 */

@Getter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class ReleasedOrderCreatedMessage extends TransactionMQMessage<String> {

    private String releasedOrderId;

    @Override
    public String getSource() {
        return releasedOrderId;
    }
}
