package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay;

import lombok.Getter;

/**
 * @author evol
 */
@Getter
public class HuanQiuPayConfig {

    private final String domain;

    private final String releasedCreateUrl = "http://api.xiongwei2000.com/pay.bank.to";

    private final String receiptCreateUrl = "http://api.xiongwei2000.com/customer.pay";

    private final String receiptNotifyUrl = "/v1/api/pay/payment/huan-qiu-pay/receipt/notify";

    private final String successUrl = "/v1/api/pay/payment/huan-qiu-pay/success";

    private final String failUrl = "/v1/api/pay/payment/huan-qiu-pay/fail";

    private final String releasedNotifyUrl = "/v1/api/pay/payment/huan-qiu-pay/released/notify";

    public HuanQiuPayConfig(String domain) {
        this.domain = domain;
    }

    public String getReleasedCreateNotifyUrl() {
        return domain + releasedNotifyUrl;
    }

    public String getReceiptCreateNotifyUrl() {
        return domain + receiptNotifyUrl;
    }

    public String getSuccessUrl() {
        return domain + successUrl;
    }

    public String getFailUrl() {
        return domain + failUrl;
    }
}


