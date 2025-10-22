package org.evolboot.pay.domain.payinorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.payinorder.repository.PayinOrderRepository;
import org.evolboot.pay.domain.payinorder.service.PayinOrderSupportService;

import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.dto.PayinOrderQueryRequest;


import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 第三方代收订单
 *
 * @author evol
 * @date 2023-06-14 20:22:47
 */
@Slf4j
@Service
public class PayinOrderQueryServiceImpl implements PayinOrderQueryService {

    private final PayinOrderRepository repository;
    private final PayinOrderSupportService supportService;

    protected PayinOrderQueryServiceImpl(PayinOrderRepository repository, PayinOrderSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public PayinOrder findById(String id) {
        return supportService.findById(id);
    }

    @Override
    public List<PayinOrder> findAll() {
        return repository.findAll();
    }


    @Override
    public List<PayinOrder> findAll(PayinOrderQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<PayinOrder> page(PayinOrderQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<PayinOrder> findOne(PayinOrderQueryRequest query) {
        return repository.findOne(query);
    }
}
