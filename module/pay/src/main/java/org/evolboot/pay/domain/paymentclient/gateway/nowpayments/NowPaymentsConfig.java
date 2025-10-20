package org.evolboot.pay.domain.paymentclient.gateway.nowpayments;

import lombok.Getter;

/**
 * NOWPayments 配置
 *
 * @author evol
 */
@Getter
public class NowPaymentsConfig {

    private final String domain;
    private final String apiKey;
    private final String ipnSecret;

    // NOWPayments API 端点
    private static final String BASE_URL = "https://api.nowpayments.io/v1";
    private static final String SANDBOX_BASE_URL = "https://api-sandbox.nowpayments.io/v1";

    private final String createPaymentUrl;
    private final String getPaymentStatusUrl;
    private final String ipnCallbackUrl;

    public NowPaymentsConfig(String domain, String apiKey, String ipnSecret, boolean sandbox) {
        this.domain = domain;
        this.apiKey = apiKey;
        this.ipnSecret = ipnSecret;

        String baseUrl = sandbox ? SANDBOX_BASE_URL : BASE_URL;
        this.createPaymentUrl = baseUrl + "/payment";
        this.getPaymentStatusUrl = baseUrl + "/payment";
        this.ipnCallbackUrl = domain + "/api/v1/pay/payment/now-payments/receipt/notify";
    }

    public String getPaymentStatusUrl(String paymentId) {
        return getPaymentStatusUrl + "/" + paymentId;
    }
}
