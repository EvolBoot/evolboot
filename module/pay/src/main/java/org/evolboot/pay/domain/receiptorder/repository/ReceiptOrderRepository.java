package org.evolboot.pay.domain.receiptorder.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.pay.domain.receiptorder.ReceiptOrder;

import java.util.List;
import java.util.Optional;

/**
 * 第三方代收订单
 *
 * @author evol
 */
public interface ReceiptOrderRepository extends BaseRepository<ReceiptOrder, Long> {

    ReceiptOrder save(ReceiptOrder receiptOrder);

    Optional<ReceiptOrder> findById(String id);


    void deleteById(String id);

    List<ReceiptOrder> findAll();


    <Q extends Query> List<ReceiptOrder> findAll(Q query);

    <Q extends Query> Optional<ReceiptOrder> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<ReceiptOrder> page(Q query);
}
