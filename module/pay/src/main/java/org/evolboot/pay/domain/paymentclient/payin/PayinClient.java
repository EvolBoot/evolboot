package org.evolboot.pay.domain.paymentclient.payin;

import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.PaymentClient;

/** 代收(收款)客户端 */
public interface PayinClient extends PaymentClient {

    /** 创建代收订单 */
    PayinCreateResponse createPayinOrder(String receiptOrderId, PayGatewayAccount account, PayinCreateRequest request);

    /** 代收异步回调通知 */
    <T extends PayinNotifyRequest> PayinNotifyResponse payinOrderNotify(PayGatewayAccount gatewayAccount, T request);

    /** 代收前端同步通知 */
    <T extends PayinRedirectNotifyRequest> PayinRedirectNotifyResponse payinOrderRedirectNotify(PayGatewayAccount gatewayAccount, T request);
}
