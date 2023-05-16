package org.evolboot.pay.domain.releasedorder;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderSendService;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderSupportService;
import org.evolboot.shared.event.pay.ReleasedOrderCreatedMessage;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代付订单
 *
 * @author evol
 */
@Service
@Slf4j
public class ReleasedOrderListener extends ReleasedOrderSupportService {

    private final ReleasedOrderSendService releasedOrderSendService;


    protected ReleasedOrderListener(ReleasedOrderRepository repository, ReleasedOrderSendService releasedOrderSendService) {
        super(repository);
        this.releasedOrderSendService = releasedOrderSendService;
    }

    @EventListener
    @Transactional
    public void on(ReleasedOrderCreatedMessage event) {
        log.info("消息:监听到代付:{}", event.getReleasedOrderId());
        releasedOrderSendService.send(event.getReleasedOrderId());
    }


}
