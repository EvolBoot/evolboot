package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.shared.pay.ReceiptOrderStatus;

/**
 * @author evol
 */

@Setter
@Getter
@AllArgsConstructor
public class HuanQiuPayReceiptRedirectNotifyRequest implements ReceiptRedirectNotifyRequest {


    @Override
    public String getReceiptOrderId() {
        return null;
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
        return ReceiptOrderStatus.PENDING;
    }
}
