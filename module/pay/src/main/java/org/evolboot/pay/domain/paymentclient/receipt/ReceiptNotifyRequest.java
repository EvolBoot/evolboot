package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.shared.pay.PayGateway;

import java.math.BigDecimal;

/**
 * 代收通知
 *
 * @author evol
 */
public interface ReceiptNotifyRequest {

    PayGateway getPayGateway();

    String getReceiptOrderId();

    String getForeignOrderId();

    String getStatus();

    String getNotifyParamsText();

    BigDecimal getPayAmount();

    BigDecimal getRealPayAmount();

    BigDecimal getPoundage();

    void checkSign(String secretKey);

    boolean isOk();
}
