package org.evolboot.pay.domain.releasedorder.service;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.releasedorder.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 代付订单
 *
 * @author evol
 */
@Slf4j
public abstract class ReleasedOrderSupportService {

    protected final ReleasedOrderRepository repository;

    protected ReleasedOrderSupportService(ReleasedOrderRepository repository) {
        this.repository = repository;
    }

    public ReleasedOrder findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(PayI18nMessage.ReleasedOrder.notFound()));
    }

}
