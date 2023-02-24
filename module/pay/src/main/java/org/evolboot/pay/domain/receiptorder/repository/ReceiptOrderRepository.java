package org.evolboot.pay.domain.receiptorder.repository;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.receiptorder.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderQuery;

import java.util.List;
import java.util.Optional;

/**
 * 第三方代收订单
 *
 * @author evol
 */
public interface ReceiptOrderRepository {

    ReceiptOrder save(ReceiptOrder receiptOrder);

    Optional<ReceiptOrder> findById(String id);

    Page<ReceiptOrder> page(ReceiptOrderQuery query);

    void deleteById(String id);

    List<ReceiptOrder> findAll();

    List<ReceiptOrder> findAll(ReceiptOrderQuery query);
}
