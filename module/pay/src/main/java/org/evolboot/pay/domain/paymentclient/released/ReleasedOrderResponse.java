package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.pay.domain.releasedorder.ReleasedOrderRequestResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 代付订单返回
 *
 * @author evol
 */
@AllArgsConstructor
@Getter
public class ReleasedOrderResponse {

    private boolean ok;

    private BigDecimal amount;

    private BigDecimal poundage;

    private String foreignOrderId;

    private String releasedOrderId;

    private ReleasedOrderRequestResult requestResult;


}
