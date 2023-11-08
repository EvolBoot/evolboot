package org.evolboot.pay.domain.releasedorder;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderCreateFactory;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderNotifyService;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderSupportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代付订单
 *
 * @author evol
 */
@Slf4j
@Service
public class ReleasedOrderAppServiceImpl implements ReleasedOrderAppService {

    private final ReleasedOrderSupportService supportService;

    private final ReleasedOrderRepository repository;

    private final ReleasedOrderCreateFactory factory;
    private final ReleasedOrderNotifyService notifyService;


    protected ReleasedOrderAppServiceImpl(ReleasedOrderRepository repository, ReleasedOrderSupportService supportService, ReleasedOrderCreateFactory factory, ReleasedOrderNotifyService notifyService) {
        this.repository = repository;
        this.supportService = supportService;
        this.factory = factory;
        this.notifyService = notifyService;
    }

    @Override
    @Transactional
    public ReleasedOrder create(ReleasedOrderCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request) {
        return notifyService.releasedOrderNotify(request);
    }


}
