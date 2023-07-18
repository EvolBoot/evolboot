package org.evolboot.pay.domain.releasedorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderCreateFactory;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderQuery;

import java.util.List;
import java.util.Optional;

/**
 * 代付订单
 *
 * @author evol
 */
public interface ReleasedOrderAppService {

    ReleasedOrder create(ReleasedOrderCreateFactory.Request request);


    <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request);


}
