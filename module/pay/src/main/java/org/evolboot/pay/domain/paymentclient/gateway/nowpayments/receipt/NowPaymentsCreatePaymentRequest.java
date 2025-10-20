package org.evolboot.pay.domain.paymentclient.gateway.nowpayments.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * NOWPayments 创建支付请求
 *
 * @author evol
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NowPaymentsCreatePaymentRequest {

    /**
     * 法币金额
     */
    @JsonProperty("price_amount")
    private BigDecimal priceAmount;

    /**
     * 法币类型 (如: usd, eur)
     */
    @JsonProperty("price_currency")
    private String priceCurrency;

    /**
     * 支付币种 (如: eth, btc, usdt)
     */
    @JsonProperty("pay_currency")
    private String payCurrency;

    /**
     * IPN 回调地址
     */
    @JsonProperty("ipn_callback_url")
    private String ipnCallbackUrl;

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
}
