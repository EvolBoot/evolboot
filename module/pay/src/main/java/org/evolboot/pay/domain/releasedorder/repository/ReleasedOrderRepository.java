package org.evolboot.pay.domain.releasedorder.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;

import java.util.List;
import java.util.Optional;

/**
 * 代付订单
 *
 * @author evol
 */
public interface ReleasedOrderRepository extends BaseRepository<ReleasedOrder, String> {

    ReleasedOrder save(ReleasedOrder releasedOrder);

    Optional<ReleasedOrder> findById(String id);

    void deleteById(String id);

    List<ReleasedOrder> findAll();

    <Q extends Query> List<ReleasedOrder> findAll(Q query);

    <Q extends Query> Optional<ReleasedOrder> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<ReleasedOrder> page(Q query);


}
