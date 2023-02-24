package org.evolboot.pay.domain.paymentclient.receipt;


import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
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
    ReceiptOrderResponse createReceiptOrder(String receiptOrderId, PayGatewayAccount account, ReceiptOrderRequest request);

    /**
     * 代收回调通知
     *
     * @param parameters
     * @param <T>
     * @return
     */
    <T extends ReceiptNotifyRequest> ReceiptNotifyResponse receiptOrderNotify(PayGatewayAccount gatewayAccount, T parameters);

}
