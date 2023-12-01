package org.evolboot.pay.domain.releasedorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderSupportService;

import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.dto.ReleasedOrderQueryRequest;


import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 代付订单
 *
 * @author evol
 * @date 2023-06-14 20:24:41
 */
@Slf4j
@Service
public class ReleasedOrderQueryServiceImpl implements ReleasedOrderQueryService {

    private final ReleasedOrderRepository repository;
    private final ReleasedOrderSupportService supportService;

    protected ReleasedOrderQueryServiceImpl(ReleasedOrderRepository repository, ReleasedOrderSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public ReleasedOrder findById(String id) {
        return supportService.findById(id);
    }

    @Override
    public List<ReleasedOrder> findAll() {
        return repository.findAll();
    }


    @Override
    public List<ReleasedOrder> findAll(ReleasedOrderQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<ReleasedOrder> page(ReleasedOrderQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<ReleasedOrder> findOne(ReleasedOrderQueryRequest query) {
        return repository.findOne(query);
    }
}
