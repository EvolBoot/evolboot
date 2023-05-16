package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.shared.pay.ReceiptOrderStatus;

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
