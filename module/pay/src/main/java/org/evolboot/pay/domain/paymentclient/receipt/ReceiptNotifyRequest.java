package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.shared.pay.PayGateway;
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

    boolean checkSign(String secretKey);

    ReceiptOrderStatus getStatus();
}
