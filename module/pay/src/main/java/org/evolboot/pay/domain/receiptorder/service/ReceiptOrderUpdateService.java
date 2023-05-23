package org.evolboot.pay.domain.receiptorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.springframework.stereotype.Service;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@Service
public class ReceiptOrderUpdateService extends ReceiptOrderSupportService {
    protected ReceiptOrderUpdateService(ReceiptOrderRepository repository) {
        super(repository);
    }

    public void execute(String id, Request request) {
        ReceiptOrder receiptOrder = findById(id);
        repository.save(receiptOrder);
    }

    public static class Request extends ReceiptOrderRequestBase {
    }

}
