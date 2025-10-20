package org.evolboot.pay.domain.paymentclient.gateway.nowpayments.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * NOWPayments 创建支付响应
 *
 * @author evol
 */
@Data
@NoArgsConstructor
public class NowPaymentsCreatePaymentResponse {

    /**
     * 支付ID
     */
    @JsonProperty("payment_id")
    private String paymentId;

    /**
     * 支付状态
     */
    @JsonProperty("payment_status")
    private String paymentStatus;

    /**
     * 收款地址
     */
    @JsonProperty("pay_address")
    private String payAddress;

    /**
     * 需支付的虚拟币数量
     */
    @JsonProperty("pay_amount")
    private BigDecimal payAmount;

    /**
     * 虚拟币类型
     */
    @JsonProperty("pay_currency")
    private String payCurrency;

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
     * 支付过期时间
     */
    @JsonProperty("expiration_estimate_date")
    private String expirationEstimateDate;

    /**
     * 是否创建成功
     */
    public boolean isSuccess() {
        return paymentId != null && payAddress != null;
    }
}
