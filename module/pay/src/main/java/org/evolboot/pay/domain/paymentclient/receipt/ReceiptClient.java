package org.evolboot.pay.domain.paymentclient.receipt;


import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.PaymentClient;

/**
 * 代收客户端
 *
 * @author evol
 */
public interface ReceiptClient extends PaymentClient {


    /**
     * 创建代收订单
     *
     * @param request
     * @return
     */
    ReceiptCreateResponse createReceiptOrder(String receiptOrderId, PayGatewayAccount account, ReceiptCreateRequest request);

    /**
     * 代收回调通知
     *
     * @param request
     * @param <T>
     * @return
     */
    <T extends ReceiptNotifyRequest> ReceiptNotifyResponse receiptOrderNotify(PayGatewayAccount gatewayAccount, T request);

    /**
     * 代收前端同步通知
     *
     * @param gatewayAccount
     * @param request
     * @param <T>
     * @return
     */
    <T extends ReceiptRedirectNotifyRequest> ReceiptRedirectNotifyResponse receiptOrderRedirectNotify(PayGatewayAccount gatewayAccount, T request);

}
