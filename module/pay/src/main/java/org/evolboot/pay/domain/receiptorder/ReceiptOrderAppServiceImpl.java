package org.evolboot.pay.domain.receiptorder;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.pay.domain.receiptorder.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@Service
public class ReceiptOrderAppServiceImpl implements ReceiptOrderAppService {


    private final ReceiptOrderCreateFactory factory;
    private final ReceiptOrderBuildRedirectUrlService redirectUrlService;
    private final ReceiptOrderNotifyService notifyService;

    private final ReceiptOrderSupportService supportService;

    private final ReceiptOrderRepository repository;


    protected ReceiptOrderAppServiceImpl(ReceiptOrderRepository repository, ReceiptOrderCreateFactory factory, ReceiptOrderBuildRedirectUrlService redirectUrlService, ReceiptOrderNotifyService notifyService, ReceiptOrderSupportService supportService) {
        this.repository = repository;
        this.factory = factory;
        this.redirectUrlService = redirectUrlService;
        this.notifyService = notifyService;
        this.supportService = supportService;
    }

    @Override
    @Transactional
    public ReceiptOrder create(ReceiptOrderCreateFactory.Request request) {
        return factory.execute(request);
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
