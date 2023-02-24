package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay;

import lombok.Getter;

/**
 * @author evol
 */
@Getter
public class HuanQiuPayConfig {

    private String domain;

    private String releasedUrl = "http://api.xiongwei2000.com/pay.bank.to";

    private String receiptUrl = "http://api.xiongwei2000.com/customer.pay";

    private String receiptNotifyUrl = "/v1/api/pay/payment/huan-qiu-pay/receipt/notify";

    private String successUrl = "/v1/api/pay/payment/huan-qiu-pay/success";

    private String failUrl = "/v1/api/pay/payment/huan-qiu-pay/fail";

    private String releasedNotifyUrl = "/v1/api/pay/payment/huan-qiu-pay/released/notify";

    public HuanQiuPayConfig(String domain) {
        this.domain = domain;
    }

    public String getReleasedNotifyUrl() {
        return domain + releasedNotifyUrl;
    }

    public String getReceiptNotifyUrl() {
        return domain + receiptNotifyUrl;
    }

    public String getSuccessUrl() {
        return domain + successUrl;
    }

    public String getFailUrl() {
        return domain + failUrl;
    }
}


