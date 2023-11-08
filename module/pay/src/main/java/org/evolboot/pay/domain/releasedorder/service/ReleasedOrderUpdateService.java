package org.evolboot.pay.domain.releasedorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.springframework.stereotype.Service;

/**
 * 代付订单
 *
 * @author evol
 */
@Slf4j
@Service
public class ReleasedOrderUpdateService {

    private final ReleasedOrderSupportService supportService;

    private final ReleasedOrderRepository repository;

    protected ReleasedOrderUpdateService(ReleasedOrderRepository repository, ReleasedOrderSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(String id, Request request) {
        ReleasedOrder releasedOrder = supportService.findById(id);
        repository.save(releasedOrder);
    }

    public static class Request extends ReleasedOrderRequestBase {
    }

}
