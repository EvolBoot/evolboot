package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
public abstract class ReceiptOrderSupportService {

    final ReceiptOrderRepository repository;

    protected ReceiptOrderSupportService(ReceiptOrderRepository repository) {
        this.repository = repository;
    }

    public ReceiptOrder findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(PayI18nMessage.ReceiptOrder.notFound()));
    }

}
