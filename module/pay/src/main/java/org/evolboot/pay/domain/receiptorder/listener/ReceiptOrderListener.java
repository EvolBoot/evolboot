package org.evolboot.pay.domain.receiptorder.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderSupportService;
import org.springframework.stereotype.Service;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Service
@Slf4j
public class ReceiptOrderListener {

    private final ReceiptOrderRepository repository;

    private final ReceiptOrderSupportService supportService;

    protected ReceiptOrderListener(ReceiptOrderRepository repository, ReceiptOrderSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

}
