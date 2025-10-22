package org.evolboot.pay.domain.payinorder.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;

import java.util.List;
import java.util.Optional;

/**
 * 第三方代收订单
 *
 * @author evol
 */
public interface PayinOrderRepository {

    PayinOrder save(PayinOrder payinOrder);

    Optional<PayinOrder> findById(String id);


    void deleteById(String id);

    List<PayinOrder> findAll();


    <Q extends Query> List<PayinOrder> findAll(Q query);

    <Q extends Query> Optional<PayinOrder> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<PayinOrder> page(Q query);
}
