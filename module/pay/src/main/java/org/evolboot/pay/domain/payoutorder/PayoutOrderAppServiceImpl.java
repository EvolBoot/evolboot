package org.evolboot.pay.domain.payoutorder;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.paymentclient.payout.PayoutNotifyRequest;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.evolboot.pay.domain.payoutorder.service.PayoutOrderCreateFactory;
import org.evolboot.pay.domain.payoutorder.service.PayoutOrderNotifyService;
import org.evolboot.pay.domain.payoutorder.service.PayoutOrderSupportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PayoutOrderAppServiceImpl implements PayoutOrderAppService {

    private final PayoutOrderSupportService supportService;
    private final PayoutOrderRepository repository;
    private final PayoutOrderCreateFactory factory;
    private final PayoutOrderNotifyService notifyService;

    protected PayoutOrderAppServiceImpl(PayoutOrderRepository repository,
                                        PayoutOrderSupportService supportService,
                                        PayoutOrderCreateFactory factory,
                                        PayoutOrderNotifyService notifyService) {
        this.repository = repository;
        this.supportService = supportService;
        this.factory = factory;
        this.notifyService = notifyService;
    }

    @Override
    @Transactional
    public PayoutOrder create(PayoutOrderCreateFactory.Request request) {
        return factory.execute(request);
    }

    @Override
    @Transactional
    public <T extends PayoutNotifyRequest> Object payoutOrderNotify(T request) {
        return notifyService.payoutOrderNotify(request);
    }
}
