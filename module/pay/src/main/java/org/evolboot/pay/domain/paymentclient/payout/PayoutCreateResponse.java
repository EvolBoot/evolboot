package org.evolboot.pay.domain.paymentclient.payout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrderCreateResult;

import java.math.BigDecimal;

/** 代付订单创建结果（第三方） */
@AllArgsConstructor
@Getter
public class PayoutCreateResponse {
    private boolean ok;
    private BigDecimal amount;
    private BigDecimal poundageAmount;
    private String foreignOrderId;
    private String payoutOrderId;
    private PayoutOrderCreateResult createResult;
}
