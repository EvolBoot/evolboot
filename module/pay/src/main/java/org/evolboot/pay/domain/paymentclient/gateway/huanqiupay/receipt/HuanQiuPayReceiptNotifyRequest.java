package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.receipt;

import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.HuanQiuPayUtil;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.shared.pay.ReceiptOrderStatus;

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

    @Override
    public ReceiptOrderStatus getStatus() {
        if ("成功".equals(requestParams.get("status"))) {
            return ReceiptOrderStatus.SUCCESS;
        }
        return ReceiptOrderStatus.FAIL;
    }

    private Map<String, String> requestParams;

    public String getBusinessnumber() {
        return requestParams.get("businessnumber");
    }


    @Override
    public String getForeignStatus() {
        return requestParams.get("status");
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
    public boolean checkSign(String secretKey) {
        HuanQiuPayUtil.checkSign(requestParams, secretKey);
        return true;
    }


    public String getAmount() {
        return requestParams.get("amount");
    }


    public String getReal_amount() {
        return requestParams.get("real_amount");
    }


}
