package org.evolboot.pay.domain.payoutorder;

import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.service.PayoutOrderCreateFactory;

/** 代付订单应用服务 */
public interface PayoutOrderAppService {

    PayoutOrder create(PayoutOrderCreateFactory.Request request);

    <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request);
}
