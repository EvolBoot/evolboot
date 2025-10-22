package org.evolboot.pay.domain.payinorder;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.repository.PayinOrderRepository;
import org.evolboot.pay.domain.payinorder.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@Service
public class PayinOrderAppServiceImpl implements PayinOrderAppService {


    private final PayinOrderCreateFactory factory;
    private final PayinOrderBuildRedirectUrlService redirectUrlService;
    private final PayinOrderNotifyService notifyService;

    private final PayinOrderSupportService supportService;

    private final PayinOrderRepository repository;


    protected PayinOrderAppServiceImpl(PayinOrderRepository repository, PayinOrderCreateFactory factory, PayinOrderBuildRedirectUrlService redirectUrlService, PayinOrderNotifyService notifyService, PayinOrderSupportService supportService) {
        this.repository = repository;
        this.factory = factory;
        this.redirectUrlService = redirectUrlService;
        this.notifyService = notifyService;
        this.supportService = supportService;
    }

    @Override
    @Transactional
    public PayinOrder create(PayinOrderCreateFactory.Request request) {
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
