package org.evolboot.pay.domain.payoutorder.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;

import java.util.List;
import java.util.Optional;

/**
 * 代付订单
 *
 * @author evol
 */
public interface PayoutOrderRepository extends BaseRepository<PayoutOrder, String> {

    PayoutOrder save(PayoutOrder payoutOrder);

    Optional<PayoutOrder> findById(String id);

    void deleteById(String id);

    List<PayoutOrder> findAll();

    <Q extends Query> List<PayoutOrder> findAll(Q query);

    <Q extends Query> Optional<PayoutOrder> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<PayoutOrder> page(Q query);


}
