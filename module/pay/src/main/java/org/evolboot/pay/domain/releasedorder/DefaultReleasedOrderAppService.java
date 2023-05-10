package org.evolboot.pay.domain.releasedorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.evolboot.pay.domain.releasedorder.service.*;
import lombok.extern.slf4j.Slf4j;
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
public class DefaultReleasedOrderAppService extends ReleasedOrderSupportService implements ReleasedOrderAppService {


    private final ReleasedOrderCreateFactory factory;
    private final ReleasedOrderNotifyService notifyService;


    protected DefaultReleasedOrderAppService(ReleasedOrderRepository repository, ReleasedOrderCreateFactory factory, ReleasedOrderNotifyService notifyService) {
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
    public List<ReleasedOrder> findAll() {
        return repository.findAll();
    }


    @Override
    public List<ReleasedOrder> findAll(ReleasedOrderQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<ReleasedOrder> page(ReleasedOrderQuery query) {
        return repository.page(query);
    }

    @Override
    @Transactional
    public <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request) {
        return notifyService.releasedOrderNotify(request);
    }


    @Override
    public Optional<ReleasedOrder> findOne(ReleasedOrderQuery query) {
        return repository.findOne(query);
    }

}
