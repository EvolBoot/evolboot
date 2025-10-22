package org.evolboot.pay.domain.payoutorder.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.evolboot.pay.domain.payoutorder.service.PayoutOrderSendService;
import org.evolboot.pay.domain.payoutorder.service.PayoutOrderSupportService;
import org.evolboot.shared.event.pay.PayoutOrderCreatedMessage;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PayoutOrderListener {

    private final PayoutOrderSupportService supportService;
    private final PayoutOrderRepository repository;
    private final PayoutOrderSendService payoutOrderSendService;

    protected PayoutOrderListener(PayoutOrderRepository repository,
                                  PayoutOrderSupportService supportService,
                                  PayoutOrderSendService payoutOrderSendService) {
        this.repository = repository;
        this.supportService = supportService;
        this.payoutOrderSendService = payoutOrderSendService;
    }

    @EventListener
    @Transactional
    public void on(PayoutOrderCreatedMessage event) {
        log.info("消息:监听到代付:{}", event.getPayoutOrderId());
        payoutOrderSendService.send(event.getPayoutOrderId());
    }
}
