package org.evolboot.pay.domain.paymentclient.payout;

import lombok.Getter;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrderQueryResult;
import org.evolboot.shared.pay.PayoutOrderState;

import java.math.BigDecimal;

/** 代付订单查询结果（第三方） */
@Getter
public class PayoutQueryResponse {

    private boolean exist;
    private BigDecimal amount;
    private BigDecimal poundageAmount;
    private String foreignOrderId;
    private String payoutOrderId;
    private PayoutOrderQueryResult requestResult;
    private PayoutOrderState state;

    public PayoutQueryResponse(boolean exist, BigDecimal amount, BigDecimal poundageAmount, String foreignOrderId, String payoutOrderId, PayoutOrderQueryResult requestResult, PayoutOrderState state) {
        this.exist = exist;
        this.amount = amount;
        this.poundageAmount = poundageAmount;
        this.foreignOrderId = foreignOrderId;
        this.payoutOrderId = payoutOrderId;
        this.requestResult = requestResult;
        this.state = state;
    }

    public PayoutQueryResponse(boolean exist) {
        this.exist = exist;
    }
}
