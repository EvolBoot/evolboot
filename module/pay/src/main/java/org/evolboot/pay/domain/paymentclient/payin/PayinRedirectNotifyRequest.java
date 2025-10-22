package org.evolboot.pay.domain.paymentclient.payin;

import org.evolboot.shared.pay.PayinOrderState;

/**
 * 代收通知
 *
 * @author evol
 */
public interface PayinRedirectNotifyRequest {


    String getReceiptOrderId();

    String getForeignState();

    String getNotifyParamsText();

    boolean checkSign(String secretKey);

    PayinOrderState getState();
}
