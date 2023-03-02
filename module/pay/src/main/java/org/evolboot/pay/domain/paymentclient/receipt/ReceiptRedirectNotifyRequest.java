package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.shared.pay.ReceiptOrderStatus;

import java.math.BigDecimal;

/**
 * 代收通知
 *
 * @author evol
 */
public interface ReceiptRedirectNotifyRequest {


    String getReceiptOrderId();

    String getForeignStatus();

    String getNotifyParamsText();

    boolean checkSign(String secretKey);

    ReceiptOrderStatus getStatus();
}
