package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.PaymentClient;
import org.evolboot.shared.pay.PayoutOrderOrgType;

/** 代付客户端 */
public interface ReleasedClient extends PaymentClient {

    /**
     * 创建下发(代付)订单
     *
     * @param request
     * @return
     */
    ReleasedCreateResponse createReleasedOrder(String releasedOrderId, PayGatewayAccount account, ReleasedCreateRequest request);


    /**
     * 下发(代付)通知
     *
     * @param request
     * @param <T>
     * @return
     */
    <T extends ReleasedNotifyRequest> ReleasedNotifyResponse releasedOrderNotify(PayGatewayAccount gatewayAccount, T request);


    /**
     * 查询订单
     *
     * @param releasedOrderId
     * @param account
     * @return
     */
    ReleasedQueryResponse queryReleasedOrder(String releasedOrderId, PayGatewayAccount account);

    /**
     * 支持下发的组织类型
     *
     * @param orgType
     * @return
     */
    boolean supportOrgType(PayoutOrderOrgType orgType);

}
