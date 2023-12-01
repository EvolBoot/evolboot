package org.evolboot.pay.domain.releasedorder;

import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderCreateFactory;

/**
 * 代付订单
 *
 * @author evol
 */
public interface ReleasedOrderAppService {

    ReleasedOrder create(ReleasedOrderCreateFactory.Request request);


    <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request);


}
