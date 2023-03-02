package org.evolboot.pay.domain.paymentclient.released;


import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.PaymentClient;

/**
 * 代付客户端
 *
 * @author evol
 */
public interface ReleasedClient extends PaymentClient {

    /**
     * 创建下发(代付)订单
     *
     * @param request
     * @return
     */
    ReleasedOrderResponse createReleasedOrder(String releasedOrderId, PayGatewayAccount account, ReleasedOrderRequest request);


    /**
     * 下发(代付)通知
     *
     * @param request
     * @param <T>
     * @return
     */
    <T extends ReleasedNotifyRequest> ReleasedNotifyResponse releasedOrderNotify(PayGatewayAccount gatewayAccount, T request);

}
