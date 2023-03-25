package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.pay.domain.receiptorder.service.*;
import lombok.extern.slf4j.Slf4j;
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
    private final ReceiptOrderBuildRedirectUrlService redirectUrlService;
    private final ReceiptOrderNotifyService notifyService;


    protected DefaultReceiptOrderAppService(ReceiptOrderRepository repository, ReceiptOrderCreateFactory factory, ReceiptOrderBuildRedirectUrlService redirectUrlService, ReceiptOrderNotifyService notifyService) {
        super(repository);
        this.factory = factory;
        this.redirectUrlService = redirectUrlService;
        this.notifyService = notifyService;
    }

    @Override
    @Transactional
    public ReceiptOrder create(ReceiptOrderCreateFactory.Request request) {
        return factory.execute(request);
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
    public <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T request) {
        return notifyService.receiptOrderNotify(request);
    }

    @Override
    @Transactional
    public <T extends ReceiptRedirectNotifyRequest> String getReceiptRedirectUrl(T request) {
        return redirectUrlService.getReceiptRedirectUrl(request);
    }


}
