package org.evolboot.pay.domain.releasedorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderCreateFactory;

import java.util.List;

/**
 * 代付订单
 *
 * @author evol
* 
 */
public interface ReleasedOrderAppService {

    ReleasedOrder create(ReleasedOrderCreateFactory.Request request);

    List<ReleasedOrder> findAll();

    List<ReleasedOrder> findAll(ReleasedOrderQuery query);

    Page<ReleasedOrder> page(ReleasedOrderQuery query);

    ReleasedOrder findById(String id);

    /**
     * 代付订单成功
     *
     * @param id
     * @param notifyResult
     */
    void success(String id, ReleasedOrderNotifyResult notifyResult);

    /**
     * 代付订单失败
     *
     * @param id
     * @param notifyResult
     */
    void fail(String id, ReleasedOrderNotifyResult notifyResult);

}
