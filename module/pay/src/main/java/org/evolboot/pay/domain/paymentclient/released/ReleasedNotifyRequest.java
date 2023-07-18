package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.shared.pay.ReleasedOrderState;

import java.math.BigDecimal;

/**
 * 代付通知
 *
 * @author evol
 */
public interface ReleasedNotifyRequest {

    String getReleasedOrderId();

    String getForeignOrderId();

    String getForeignState();

    String getNotifyParamsText();

    BigDecimal getAmount();

    BigDecimal getPoundage();

    ReleasedOrderState getState();

    boolean checkSign(PayGatewayAccount payGatewayAccount);
}
