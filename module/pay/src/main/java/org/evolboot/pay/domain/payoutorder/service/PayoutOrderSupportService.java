package org.evolboot.pay.domain.payoutorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayoutOrderSupportService {

    protected final PayoutOrderRepository repository;

    protected PayoutOrderSupportService(PayoutOrderRepository repository) {
        this.repository = repository;
    }

    public PayoutOrder findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(PayI18nMessage.PayoutOrder.notFound()));
    }
}
