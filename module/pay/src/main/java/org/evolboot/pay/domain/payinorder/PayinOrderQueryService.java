package org.evolboot.pay.domain.payinorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.dto.PayinOrderQueryRequest;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 第三方代收订单
 *
 * @author evol
 * @date 2023-06-14 20:22:47
 */
public interface PayinOrderQueryService {

    PayinOrder findById(String id);

    List<PayinOrder> findAll();

    List<PayinOrder> findAll(PayinOrderQueryRequest query);

    Page<PayinOrder> page(PayinOrderQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<PayinOrder> findOne(PayinOrderQueryRequest query);


}
