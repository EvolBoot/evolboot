package org.evolboot.pay.domain.paymentclient.released;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrderCreateResult;

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
