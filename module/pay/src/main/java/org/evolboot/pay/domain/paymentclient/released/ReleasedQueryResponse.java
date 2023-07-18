package org.evolboot.pay.domain.paymentclient.released;

import lombok.Getter;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrderQueryResult;
import org.evolboot.shared.pay.ReleasedOrderState;

import java.math.BigDecimal;

/**
 * 代付订单返回
 *
 * @author evol
 */
@Getter
public class ReleasedQueryResponse {

    private boolean exist;

    private BigDecimal amount;

    private BigDecimal poundageAmount;

    private String foreignOrderId;

    private String releasedOrderId;

    private ReleasedOrderQueryResult requestResult;

    private ReleasedOrderState state;

    public ReleasedQueryResponse(boolean exist, BigDecimal amount, BigDecimal poundageAmount, String foreignOrderId, String releasedOrderId, ReleasedOrderQueryResult requestResult, ReleasedOrderState state) {
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
