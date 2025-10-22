package org.evolboot.pay.domain.payoutorder;

import org.evolboot.pay.domain.paymentclient.payout.PayoutNotifyRequest;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.service.PayoutOrderCreateFactory;

/** 代付订单应用服务 */
public interface PayoutOrderAppService {

    PayoutOrder create(PayoutOrderCreateFactory.Request request);

    <T extends PayoutNotifyRequest> Object payoutOrderNotify(T request);
}
