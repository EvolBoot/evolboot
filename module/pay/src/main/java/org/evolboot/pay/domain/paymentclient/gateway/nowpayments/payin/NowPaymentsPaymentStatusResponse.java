package org.evolboot.pay.domain.paymentclient.gateway.nowpayments.payin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * NOWPayments 查询支付状态响应
 *
 * @author evol
 */
@Data
@NoArgsConstructor
public class NowPaymentsPaymentStatusResponse {

    @JsonProperty("payment_id")
    private String paymentId;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("pay_address")
    private String payAddress;

    @JsonProperty("price_amount")
    private BigDecimal priceAmount;

    @JsonProperty("price_currency")
    private String priceCurrency;

    @JsonProperty("pay_amount")
    private BigDecimal payAmount;

    @JsonProperty("actually_paid")
    private BigDecimal actuallyPaid;

    @JsonProperty("pay_currency")
    private String payCurrency;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_description")
    private String orderDescription;

    @JsonProperty("outcome_amount")
    private BigDecimal outcomeAmount;

    @JsonProperty("outcome_currency")
    private String outcomeCurrency;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * 是否支付成功
     */
    public boolean isFinished() {
        return "finished".equalsIgnoreCase(paymentStatus) || "confirmed".equalsIgnoreCase(paymentStatus);
    }

    /**
     * 是否支付失败
     */
    public boolean isFailed() {
        return "failed".equalsIgnoreCase(paymentStatus)
            || "refunded".equalsIgnoreCase(paymentStatus)
            || "expired".equalsIgnoreCase(paymentStatus);
    }
}
