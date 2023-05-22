package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.released;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.HuanQiuPayUtil;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.shared.pay.ReleasedOrderStatus;

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
    public boolean checkSign(PayGatewayAccount payGatewayAccount) {
        HuanQiuPayUtil.checkSign(notifyParams, payGatewayAccount.getSecretKey());
        return true;
    }

    public String getForeignStatus() {
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

    @Override
    public ReleasedOrderStatus getStatus() {
        if ("成功".equals(getForeignStatus())) {
            return ReleasedOrderStatus.SUCCESS;
        } else if ("失败".equals(getForeignStatus())) {
            return ReleasedOrderStatus.FAIL;
        }
        return ReleasedOrderStatus.PENDING;
    }

}
