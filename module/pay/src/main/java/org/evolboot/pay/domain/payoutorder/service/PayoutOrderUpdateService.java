package org.evolboot.pay.domain.payoutorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.payoutorder.dto.PayoutOrderRequestBase;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayoutOrderUpdateService {

    private final PayoutOrderSupportService supportService;
    private final PayoutOrderRepository repository;

    protected PayoutOrderUpdateService(PayoutOrderRepository repository, PayoutOrderSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(String id, Request request) {
        PayoutOrder payoutOrder = supportService.findById(id);
        repository.save(payoutOrder);
    }

    public static class Request extends PayoutOrderRequestBase {
    }
}
