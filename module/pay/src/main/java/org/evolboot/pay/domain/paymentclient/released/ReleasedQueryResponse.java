package org.evolboot.pay.domain.paymentclient.released;

import lombok.Getter;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrderQueryResult;
import org.evolboot.shared.pay.PayoutOrderState;

import java.math.BigDecimal;

/** 代付订单查询结果（第三方） */
@Getter
public class ReleasedQueryResponse {

    private boolean exist;
    private BigDecimal amount;
    private BigDecimal poundageAmount;
    private String foreignOrderId;
    private String releasedOrderId;
    private PayoutOrderQueryResult requestResult;
    private PayoutOrderState state;

    public ReleasedQueryResponse(boolean exist, BigDecimal amount, BigDecimal poundageAmount, String foreignOrderId, String releasedOrderId, PayoutOrderQueryResult requestResult, PayoutOrderState state) {
        this.exist = exist;
        this.amount = amount;
        this.poundageAmount = poundageAmount;
        this.foreignOrderId = foreignOrderId;
        this.releasedOrderId = releasedOrderId;
        this.requestResult = requestResult;
        this.state = state;
    }

    public ReleasedQueryResponse(boolean exist) {
        this.exist = exist;
    }
}
