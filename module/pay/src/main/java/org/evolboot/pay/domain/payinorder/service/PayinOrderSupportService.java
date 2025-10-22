package org.evolboot.pay.domain.payinorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.repository.PayinOrderRepository;
import org.springframework.stereotype.Service;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@Service
public class PayinOrderSupportService {

    protected final PayinOrderRepository repository;

    protected PayinOrderSupportService(PayinOrderRepository repository) {
        this.repository = repository;
    }

    public PayinOrder findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(PayI18nMessage.PayinOrder.notFound()));
    }

}
