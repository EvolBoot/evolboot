package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.receipt;

import lombok.AllArgsConstructor;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.shared.pay.ReceiptOrderStatus;

/**
 * @author evol
 */
@AllArgsConstructor
public class HuanQiuPayReceiptRedirectNotifyRequest implements ReceiptRedirectNotifyRequest {

    private  String receiptOrderId;

    @Override
    public String getReceiptOrderId() {
        return receiptOrderId;
    }

    @Override
    public String getForeignStatus() {
        return null;
    }

    @Override
    public String getNotifyParamsText() {
        return null;
    }

    @Override
    public boolean checkSign(String secretKey) {
        return false;
    }

    @Override
    public ReceiptOrderStatus getStatus() {
        return null;
    }
}
