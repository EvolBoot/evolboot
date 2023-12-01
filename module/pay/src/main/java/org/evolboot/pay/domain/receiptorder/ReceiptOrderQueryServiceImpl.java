package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderSupportService;

import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.dto.ReceiptOrderQueryRequest;


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
public class ReceiptOrderQueryServiceImpl implements ReceiptOrderQueryService {

    private final ReceiptOrderRepository repository;
    private final ReceiptOrderSupportService supportService;

    protected ReceiptOrderQueryServiceImpl(ReceiptOrderRepository repository, ReceiptOrderSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public ReceiptOrder findById(String id) {
        return supportService.findById(id);
    }

    @Override
    public List<ReceiptOrder> findAll() {
        return repository.findAll();
    }


    @Override
    public List<ReceiptOrder> findAll(ReceiptOrderQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<ReceiptOrder> page(ReceiptOrderQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<ReceiptOrder> findOne(ReceiptOrderQueryRequest query) {
        return repository.findOne(query);
    }
}
