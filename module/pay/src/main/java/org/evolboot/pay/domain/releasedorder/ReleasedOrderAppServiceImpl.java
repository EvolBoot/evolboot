package org.evolboot.pay.domain.releasedorder;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderCreateFactory;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderNotifyService;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderQuery;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderSupportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 代付订单
 *
 * @author evol
 */
@Slf4j
@Service
public class ReleasedOrderAppServiceImpl extends ReleasedOrderSupportService implements ReleasedOrderAppService {


    private final ReleasedOrderCreateFactory factory;
    private final ReleasedOrderNotifyService notifyService;


    protected ReleasedOrderAppServiceImpl(ReleasedOrderRepository repository, ReleasedOrderCreateFactory factory, ReleasedOrderNotifyService notifyService) {
        super(repository);
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
