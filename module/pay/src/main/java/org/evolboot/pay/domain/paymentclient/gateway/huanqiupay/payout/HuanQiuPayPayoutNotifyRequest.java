package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.payout;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.HuanQiuPayUtil;
import org.evolboot.pay.domain.paymentclient.payout.PayoutNotifyRequest;
import org.evolboot.shared.pay.PayoutOrderState;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author evol
 */
@Getter
@Setter
public class HuanQiuPayPayoutNotifyRequest implements PayoutNotifyRequest {

    private Map<String, String> notifyParams;

    @Override
    public boolean checkSign(PayGatewayAccount payGatewayAccount) {
        HuanQiuPayUtil.checkSign(notifyParams, payGatewayAccount.getSecretKey());
        return true;
    }

    public String getForeignState() {
        return notifyParams.get("state");
    }

    @Override
    public String getNotifyParamsText() {
        return null;
    }

    public HuanQiuPayPayoutNotifyRequest(Map<String, String> notifyParams) {
        this.notifyParams = notifyParams;
    }

    @Override
    public String getPayoutOrderId() {
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

    @Override
    public PayoutOrderState getState() {
        if ("成功".equals(getForeignState())) {
            return PayoutOrderState.SUCCESS;
        } else if ("失败".equals(getForeignState())) {
            return PayoutOrderState.FAIL;
        }
        return PayoutOrderState.PENDING;
    }

}
