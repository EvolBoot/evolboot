package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.HuanQiuPayUtil;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.shared.pay.PayinOrderState;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author evol
 */

@Setter
@Getter
@AllArgsConstructor
public class HuanQiuPayReceiptNotifyRequest implements ReceiptNotifyRequest {


    @Override
    public String getReceiptOrderId() {
        return requestParams.get("businessnumber");
    }

    @Override
    public String getForeignOrderId() {
        return null;
    }


    private Map<String, String> requestParams;

    public String getBusinessnumber() {
        return requestParams.get("businessnumber");
    }


    @Override
    public String getForeignState() {
        return requestParams.get("state");
    }

    @Override
    public String getNotifyParamsText() {
        return JsonUtil.stringify(requestParams);
    }

    @Override
    public BigDecimal getPayAmount() {
        return new BigDecimal(getAmount());
    }

    @Override
    public BigDecimal getRealPayAmount() {
        return getPayAmount();
    }

    @Override
    public BigDecimal getPoundage() {
        return null;
    }

    @Override
    public boolean checkSign(PayGatewayAccount payGatewayAccount) {
        HuanQiuPayUtil.checkSign(requestParams, payGatewayAccount.getSecretKey());
        return true;
    }

    @Override
    public PayinOrderState getState() {
        if ("成功".equals(requestParams.get("state"))) {
            return PayinOrderState.SUCCESS;
        }
        return PayinOrderState.FAIL;
    }


    public String getAmount() {
        return requestParams.get("amount");
    }


    public String getReal_amount() {
        return requestParams.get("real_amount");
    }


}
