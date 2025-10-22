package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.shared.pay.PayinOrderState;

import java.math.BigDecimal;

/**
 * 代收通知
 *
 * @author evol
 */
public interface ReceiptNotifyRequest {

    String getReceiptOrderId();

    String getForeignOrderId();

    String getForeignState();

    String getNotifyParamsText();

    BigDecimal getPayAmount();

    BigDecimal getRealPayAmount();

    BigDecimal getPoundage();

    boolean checkSign(PayGatewayAccount payGatewayAccount);

    PayinOrderState getState();
}
