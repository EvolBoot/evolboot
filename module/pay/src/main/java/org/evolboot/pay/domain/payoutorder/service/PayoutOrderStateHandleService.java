package org.evolboot.pay.domain.payoutorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.evolboot.shared.event.pay.PayoutOrderStateChangeMessage;
import org.evolboot.shared.pay.PayoutOrderState;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayoutOrderStateHandleService {

    private final PayoutOrderSupportService supportService;
    private final PayoutOrderRepository repository;
    private final MQMessagePublisher mqMessagePublisher;

    protected PayoutOrderStateHandleService(PayoutOrderRepository repository,
                                            PayoutOrderSupportService supportService,
                                            MQMessagePublisher mqMessagePublisher) {
        this.supportService = supportService;
        this.repository = repository;
        this.mqMessagePublisher = mqMessagePublisher;
    }

    public void success(PayoutOrder payoutOrder) {
        log.info("代付:成功:{}", payoutOrder.id());
        boolean success = payoutOrder.success();
        if (success) {
            mqMessagePublisher.sendMessageInTransaction(new PayoutOrderStateChangeMessage(
                    payoutOrder.id(),
                    payoutOrder.getForeignOrderId(),
                    payoutOrder.getInternalOrderId(),
                    payoutOrder.getAmount(),
                    PayoutOrderState.SUCCESS));
        }
        repository.save(payoutOrder);
    }

    public void fail(PayoutOrder payoutOrder) {
        log.info("代付:失败:{}", payoutOrder.id());
        boolean fail = payoutOrder.fail();
        if (fail) {
            mqMessagePublisher.sendMessageInTransaction(new PayoutOrderStateChangeMessage(
                    payoutOrder.id(),
                    payoutOrder.getForeignOrderId(),
                    payoutOrder.getInternalOrderId(),
                    payoutOrder.getAmount(),
                    PayoutOrderState.FAIL
            ));
        }
        repository.save(payoutOrder);
    }

    public void pending(PayoutOrder payoutOrder, BigDecimal poundage, String foreignOrderId) {
        log.info("代付:Pending:{}", payoutOrder.id());
        payoutOrder.pending(poundage, foreignOrderId);
        repository.save(payoutOrder);
    }
}
