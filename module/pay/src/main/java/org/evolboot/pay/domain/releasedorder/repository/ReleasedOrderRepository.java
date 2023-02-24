package org.evolboot.pay.domain.releasedorder.repository;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.releasedorder.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderQuery;

import java.util.List;
import java.util.Optional;

/**
 * 代付订单
 *
 * @author evol
 */
public interface ReleasedOrderRepository {

    ReleasedOrder save(ReleasedOrder releasedOrder);

    Optional<ReleasedOrder> findById(String id);

    Page<ReleasedOrder> page(ReleasedOrderQuery query);

    void deleteById(String id);

    List<ReleasedOrder> findAll();

    List<ReleasedOrder> findAll(ReleasedOrderQuery query);
}
