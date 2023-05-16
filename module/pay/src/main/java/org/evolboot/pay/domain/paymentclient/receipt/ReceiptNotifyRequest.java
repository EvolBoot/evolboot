package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.shared.pay.ReceiptOrderStatus;

import java.math.BigDecimal;

/**
 * 代收通知
 *
 * @author evol
 */
public interface ReceiptNotifyRequest {

    String getReceiptOrderId();

    String getForeignOrderId();

    String getForeignStatus();

    String getNotifyParamsText();

    BigDecimal getPayAmount();

    BigDecimal getRealPayAmount();

    BigDecimal getPoundage();

    boolean checkSign(PayGatewayAccount payGatewayAccount);

    ReceiptOrderStatus getStatus();
}
