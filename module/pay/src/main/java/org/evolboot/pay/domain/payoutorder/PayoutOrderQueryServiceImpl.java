package org.evolboot.pay.domain.payoutorder;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.payoutorder.dto.PayoutOrderQueryRequest;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.evolboot.pay.domain.payoutorder.service.PayoutOrderSupportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PayoutOrderQueryServiceImpl implements PayoutOrderQueryService {

    private final PayoutOrderRepository repository;
    private final PayoutOrderSupportService supportService;

    protected PayoutOrderQueryServiceImpl(PayoutOrderRepository repository, PayoutOrderSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public PayoutOrder findById(String id) {
        return supportService.findById(id);
    }

    @Override
    public List<PayoutOrder> findAll() {
        return repository.findAll();
    }

    @Override
    public List<PayoutOrder> findAll(PayoutOrderQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<PayoutOrder> page(PayoutOrderQueryRequest query) {
        return repository.page(query);
    }

    @Override
    public Optional<PayoutOrder> findOne(PayoutOrderQueryRequest query) {
        return repository.findOne(query);
    }
}
