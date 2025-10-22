package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.shared.pay.PayoutOrderState;

import java.math.BigDecimal;

/** 第三方代付通知请求 */
public interface ReleasedNotifyRequest {

    String getReleasedOrderId();

    String getForeignOrderId();

    String getForeignState();

    String getNotifyParamsText();

    BigDecimal getAmount();

    BigDecimal getPoundage();

    PayoutOrderState getState();

    boolean checkSign(PayGatewayAccount payGatewayAccount);
}
