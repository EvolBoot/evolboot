package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderCreateFactory;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderStatusHandleService;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderSupportService;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderUpdateService;
import org.evolboot.shared.pay.ReceiptOrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@Service
public class DefaultReceiptOrderAppService extends ReceiptOrderSupportService implements ReceiptOrderAppService {


    private final ReceiptOrderCreateFactory factory;
    private final ReceiptOrderUpdateService updateService;
    private final ReceiptOrderStatusHandleService statusHandleService;
    private final ReceiptOrderBuildRedirectUrlService receiptOrderBuildRedirectUrlService;


    protected DefaultReceiptOrderAppService(ReceiptOrderRepository repository, ReceiptOrderCreateFactory factory, ReceiptOrderUpdateService updateService, ReceiptOrderStatusHandleService statusHandleService, ReceiptOrderBuildRedirectUrlService receiptOrderBuildRedirectUrlService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
        this.statusHandleService = statusHandleService;
        this.receiptOrderBuildRedirectUrlService = receiptOrderBuildRedirectUrlService;
    }

    @Override
    @Transactional
    public ReceiptOrder create(ReceiptOrderCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(String id, ReceiptOrderUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<ReceiptOrder> findAll() {
        return repository.findAll();
    }


    @Override
    public List<ReceiptOrder> findAll(ReceiptOrderQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<ReceiptOrder> page(ReceiptOrderQuery query) {
        return repository.page(query);
    }

    @Override
    @Transactional
    public void success(String id, ReceiptOrderNotifyResult receiptOrderNotifyResult) {
        statusHandleService.success(id, receiptOrderNotifyResult);
    }

    @Override
    @Transactional
    public void fail(String id, ReceiptOrderNotifyResult receiptOrderNotifyResult) {
        statusHandleService.fail(id, receiptOrderNotifyResult);
    }

    @Override
    public String getRedirectUrl(String id, ReceiptOrderStatus status) {
        return receiptOrderBuildRedirectUrlService.execute(id, status);
    }


}
