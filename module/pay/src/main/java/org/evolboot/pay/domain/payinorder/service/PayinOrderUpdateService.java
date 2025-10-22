package org.evolboot.pay.domain.payinorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.payinorder.dto.PayinOrderRequestBase;
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
public class PayinOrderUpdateService {

    private final PayinOrderSupportService supportService;

    private final PayinOrderRepository repository;

    protected PayinOrderUpdateService(PayinOrderRepository repository, PayinOrderSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(String id, Request request) {
        PayinOrder receiptOrder = supportService.findById(id);
        repository.save(receiptOrder);
    }

    public static class Request extends PayinOrderRequestBase {
    }

}
