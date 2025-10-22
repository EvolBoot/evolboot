package org.evolboot.pay.domain.payinorder.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.payinorder.repository.PayinOrderRepository;
import org.evolboot.pay.domain.payinorder.service.PayinOrderSupportService;
import org.springframework.stereotype.Service;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Service
@Slf4j
public class PayinOrderListener {

    private final PayinOrderRepository repository;

    private final PayinOrderSupportService supportService;

    protected PayinOrderListener(PayinOrderRepository repository, PayinOrderSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

}
