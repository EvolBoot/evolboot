package org.evolboot.pay.domain.paymentclient.gateway.nowpayments.payin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.gateway.nowpayments.NowPaymentsUtil;
import org.evolboot.pay.domain.paymentclient.payin.PayinNotifyRequest;
import org.evolboot.shared.pay.PayinOrderState;

import java.math.BigDecimal;

/**
 * NOWPayments IPN 回调通知请求
 *
 * @author evol
 */
@Data
@NoArgsConstructor
public class NowPaymentsPayinNotifyRequest implements PayinNotifyRequest {

    /**
     * 支付ID
     */
    @JsonProperty("payment_id")
    private String paymentId;

    /**
     * 支付状态: waiting, confirming, confirmed, sending, finished, failed, refunded, expired
     */
    @JsonProperty("payment_status")
    private String paymentStatus;

    /**
     * 收款地址
     */
    @JsonProperty("pay_address")
    private String payAddress;

    /**
     * 法币金额
     */
    @JsonProperty("price_amount")
    private BigDecimal priceAmount;

    /**
     * 法币类型
     */
    @JsonProperty("price_currency")
    private String priceCurrency;

    /**
     * 需支付的虚拟币数量
     */
    @JsonProperty("pay_amount")
    private BigDecimal payAmount;

    /**
     * 实际支付的虚拟币数量
     */
    @JsonProperty("actually_paid")
    private BigDecimal actuallyPaid;

    /**
     * 虚拟币类型
     */
    @JsonProperty("pay_currency")
    private String payCurrency;

    /**
     * 订单ID
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 订单描述
     */
    @JsonProperty("order_description")
    private String orderDescription;

    /**
     * 结算金额
     */
    @JsonProperty("outcome_amount")
    private BigDecimal outcomeAmount;

    /**
     * 结算币种
     */
    @JsonProperty("outcome_currency")
    private String outcomeCurrency;

    /**
     * 原始通知文本(需要手动设置)
     */
    private String notifyParamsText;

    /**
     * IPN 签名(从 header 中获取,需要手动设置)
     */
    private String ipnSignature;

    @Override
    public String getForeignOrderId() {
        return paymentId;
    }

    @Override
    public String getPayinOrderId() {
        return orderId;
    }

    @Override
    public PayinOrderState getState() {
        return convertPaymentStatus(paymentStatus);
    }

    @Override
    public String getForeignState() {
        return paymentStatus;
    }

    @Override
    public String getNotifyParamsText() {
        return notifyParamsText;
    }

    @Override
    public BigDecimal getPayAmount() {
        return priceAmount;
    }

    @Override
    public BigDecimal getRealPayAmount() {
        return outcomeAmount != null ? outcomeAmount : priceAmount;
    }

    @Override
    public BigDecimal getPoundage() {
        // NOWPayments 手续费 = 法币金额 - 实际结算金额
        if (outcomeAmount != null && priceAmount != null) {
            return priceAmount.subtract(outcomeAmount);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public boolean checkSign(PayGatewayAccount payGatewayAccount) {
        // 使用 IPN Secret 验证签名
        String ipnSecret = payGatewayAccount.getSecretKey();
        return NowPaymentsUtil.verifyIpnSignature(
            notifyParamsText,
            ipnSignature,
            ipnSecret
        );
    }

    /**
     * 转换 NOWPayments 支付状态到系统状态
     */
    private PayinOrderState convertPaymentStatus(String status) {
        if (status == null) {
            return PayinOrderState.PENDING;
        }
        switch (status.toLowerCase()) {
            case "finished":
            case "confirmed":
                return PayinOrderState.SUCCESS;
            case "failed":
            case "refunded":
            case "expired":
                return PayinOrderState.FAIL;
            case "waiting":
            case "confirming":
            case "sending":
            default:
                return PayinOrderState.PENDING;
        }
    }
}
