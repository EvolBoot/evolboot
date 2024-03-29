package org.evolboot.pay.domain.releasedorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.dto.ReleasedOrderQueryRequest;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 代付订单
 *
 * @author evol
 * @date 2023-06-14 20:24:41
 */
public interface ReleasedOrderQueryService {

    ReleasedOrder findById(String id);

    List<ReleasedOrder> findAll();

    List<ReleasedOrder> findAll(ReleasedOrderQueryRequest query);

    Page<ReleasedOrder> page(ReleasedOrderQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<ReleasedOrder> findOne(ReleasedOrderQueryRequest query);


}
