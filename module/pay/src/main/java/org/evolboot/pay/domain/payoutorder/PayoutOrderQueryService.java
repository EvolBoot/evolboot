package org.evolboot.pay.domain.payoutorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.payoutorder.dto.PayoutOrderQueryRequest;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;

import java.util.List;
import java.util.Optional;

/** 查询服务 代付订单 */
public interface PayoutOrderQueryService {

    PayoutOrder findById(String id);

    List<PayoutOrder> findAll();

    List<PayoutOrder> findAll(PayoutOrderQueryRequest query);

    Page<PayoutOrder> page(PayoutOrderQueryRequest query);

    Optional<PayoutOrder> findOne(PayoutOrderQueryRequest query);
}
