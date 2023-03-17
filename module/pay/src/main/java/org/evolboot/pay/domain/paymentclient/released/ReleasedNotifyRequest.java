package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReleasedOrderStatus;

import java.math.BigDecimal;

/**
 * 代付通知
 *
 * @author evol
 */
public interface ReleasedNotifyRequest {

    String getReleasedOrderId();

    String getForeignOrderId();

    String getForeignStatus();

    String getNotifyParamsText();

    BigDecimal getAmount();

    BigDecimal getPoundage();

    ReleasedOrderStatus getStatus();

    boolean checkSign(PayGatewayAccount payGatewayAccount);
}
