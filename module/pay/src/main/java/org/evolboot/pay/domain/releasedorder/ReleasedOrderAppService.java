package org.evolboot.pay.domain.releasedorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderCreateFactory;

import java.util.List;
import java.util.Optional;

/**
 * 代付订单
 *
 * @author evol
 */
public interface ReleasedOrderAppService {

    ReleasedOrder create(ReleasedOrderCreateFactory.Request request);

    List<ReleasedOrder> findAll();

    List<ReleasedOrder> findAll(ReleasedOrderQuery query);

    Page<ReleasedOrder> page(ReleasedOrderQuery query);

    ReleasedOrder findById(String id);


    <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<ReleasedOrder> findOne(ReleasedOrderQuery query);


}
