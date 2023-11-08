package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.pay.PayAccessAuthorities;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderCreateFactory;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderSupportService;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderUpdateService;

import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public List<ReceiptOrder> findAll(ReceiptOrderQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<ReceiptOrder> page(ReceiptOrderQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<ReceiptOrder> findOne(ReceiptOrderQuery query) {
        return repository.findOne(query);
    }
}
