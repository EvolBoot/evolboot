package org.evolboot.pay.domain.releasedorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 代付订单
 *
 * @author evol
 */
@Slf4j
@Service
public class DefaultReleasedOrderAppService extends ReleasedOrderSupportService implements ReleasedOrderAppService {


    private final ReleasedOrderCreateFactory factory;
    private final ReleasedOrderUpdateService updateService;
    private final ReleasedOrderStatusHandleService statusHandleService;

    protected DefaultReleasedOrderAppService(ReleasedOrderRepository repository, ReleasedOrderCreateFactory factory, ReleasedOrderUpdateService updateService, ReleasedOrderStatusHandleService statusHandleService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
        this.statusHandleService = statusHandleService;
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
    public void success(String id, ReleasedOrderNotifyResult notifyResult) {
        statusHandleService.success(id, notifyResult);
    }

    @Override
    @Transactional
    public void fail(String id, ReleasedOrderNotifyResult notifyResult) {
        statusHandleService.fail(id, notifyResult);
    }

}
