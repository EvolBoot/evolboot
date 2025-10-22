package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.payin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.pay.domain.paymentclient.payin.PayinRedirectNotifyRequest;
import org.evolboot.shared.pay.PayinOrderState;

/**
 * @author evol
 */

@Setter
@Getter
@AllArgsConstructor
public class HuanQiuPayPayinRedirectNotifyRequest implements PayinRedirectNotifyRequest {


    @Override
    public String getPayinOrderId() {
        return null;
    }

    @Override
    public String getForeignState() {
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
    public PayinOrderState getState() {
        return PayinOrderState.PENDING;
    }
}
