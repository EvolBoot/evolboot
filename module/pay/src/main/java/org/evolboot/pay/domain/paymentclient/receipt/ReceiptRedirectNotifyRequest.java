package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.shared.pay.ReceiptOrderState;

/**
 * 代收通知
 *
 * @author evol
 */
public interface ReceiptRedirectNotifyRequest {


    String getReceiptOrderId();

    String getForeignState();

    String getNotifyParamsText();

    boolean checkSign(String secretKey);

    ReceiptOrderState getState();
}
