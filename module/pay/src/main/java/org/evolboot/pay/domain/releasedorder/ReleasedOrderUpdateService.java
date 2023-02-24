package org.evolboot.pay.domain.releasedorder;

import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 代付订单
 *
 * @author evol
 */
@Slf4j
@Service
public class ReleasedOrderUpdateService extends ReleasedOrderSupportService {
    protected ReleasedOrderUpdateService(ReleasedOrderRepository repository) {
        super(repository);
    }

    public void execute(String id, Request request) {
        ReleasedOrder releasedOrder = findById(id);
        repository.save(releasedOrder);
    }

    public static class Request extends ReleasedOrderRequestBase {
    }

}
