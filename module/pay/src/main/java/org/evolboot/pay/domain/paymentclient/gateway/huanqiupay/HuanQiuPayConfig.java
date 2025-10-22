package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay;

import lombok.Getter;

/**
 * @author evol
 */
@Getter
public class HuanQiuPayConfig {

    private final String domain;

    private final String payoutCreateUrl = "http://api.xiongwei2000.com/pay.bank.to";

    private final String payinCreateUrl = "http://api.xiongwei2000.com/customer.pay";

    private final String payinNotifyUrl = "/api/v1/pay/payment/huan-qiu-pay/payin/notify";

    private final String successUrl = "/api/v1/pay/payment/huan-qiu-pay/success";

    private final String failUrl = "/api/v1/pay/payment/huan-qiu-pay/fail";

    private final String payoutNotifyUrl = "/api/v1/pay/payment/huan-qiu-pay/payout/notify";

    public HuanQiuPayConfig(String domain) {
        this.domain = domain;
    }

    public String getPayoutCreateNotifyUrl() {
        return domain + payoutNotifyUrl;
    }

    public String getPayinCreateNotifyUrl() {
        return domain + payinNotifyUrl;
    }

    public String getSuccessUrl() {
        return domain + successUrl;
    }

    public String getFailUrl() {
        return domain + failUrl;
    }
}

