package org.evolboot.pay.domain.paymentclient.payout;

import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.PaymentClient;
import org.evolboot.shared.pay.PayoutOrderOrgType;

/** 代付客户端 */
public interface PayoutClient extends PaymentClient {

    /**
     * 创建下发(代付)订单
     *
     * @param request
     * @return
     */
    PayoutCreateResponse createPayoutOrder(String payoutOrderId, PayGatewayAccount account, PayoutCreateRequest request);


    /**
     * 下发(代付)通知
     *
     * @param request
     * @param <T>
     * @return
     */
    <T extends PayoutNotifyRequest> PayoutNotifyResponse payoutOrderNotify(PayGatewayAccount gatewayAccount, T request);


    /**
     * 查询订单
     *
     * @param payoutOrderId
     * @param account
     * @return
     */
    PayoutQueryResponse queryPayoutdOrder(String payoutOrderId, PayGatewayAccount account);

    /**
     * 支持下发的组织类型
     *
     * @param orgType
     * @return
     */
    boolean supportOrgType(PayoutOrderOrgType orgType);

}
