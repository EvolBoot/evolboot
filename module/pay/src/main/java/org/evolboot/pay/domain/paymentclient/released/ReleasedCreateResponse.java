package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.pay.domain.releasedorder.ReleasedOrderCreateResult;
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
public class ReleasedCreateResponse {

    private boolean ok;

    private BigDecimal amount;

    private BigDecimal poundageAmount;

    private String foreignOrderId;

    private String releasedOrderId;

    private ReleasedOrderCreateResult createResult;


}
