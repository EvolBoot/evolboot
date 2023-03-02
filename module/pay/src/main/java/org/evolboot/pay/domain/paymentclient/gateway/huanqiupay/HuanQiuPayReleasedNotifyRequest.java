package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay;

import org.evolboot.shared.pay.PayGateway;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author evol
 */
@Getter
@Setter
public class HuanQiuPayReleasedNotifyRequest implements ReleasedNotifyRequest {

    private Map<String, String> notifyParams;

    @Override
    public boolean isOk() {
        return "成功".equals(getStatus());
    }

    @Override
    public boolean checkSign(String secretKey) {
        HuanQiuPayUtil.checkSign(notifyParams, secretKey);
        return true;
    }

    public String getStatus() {
        return notifyParams.get("status");
    }

    @Override
    public String getNotifyParamsText() {
        return null;
    }

    public HuanQiuPayReleasedNotifyRequest(Map<String, String> notifyParams) {
        this.notifyParams = notifyParams;
    }

    @Override
    public PayGateway getPayGateway() {
        return PayGateway.HUANQIU_PAY;
    }

    @Override
    public String getReleasedOrderId() {
        return notifyParams.get("businessnumber");
    }

    @Override
    public String getForeignOrderId() {
        return null;
    }

    public BigDecimal getAmount() {
        return new BigDecimal(notifyParams.get("amount"));
    }

    @Override
    public BigDecimal getPoundage() {
        return null;
    }
}
