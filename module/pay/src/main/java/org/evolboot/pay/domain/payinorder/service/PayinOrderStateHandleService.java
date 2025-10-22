package org.evolboot.pay.domain.payinorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.entity.PayinOrderNotifyResult;
import org.evolboot.pay.domain.payinorder.repository.PayinOrderRepository;
import org.evolboot.shared.event.pay.PayinOrderStateChangeMessage;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class PayinOrderStateHandleService {

    private final PayinOrderRepository repository;

    private final PayinOrderSupportService supportService;


    private final MQMessagePublisher mqMessagePublisher;

    protected PayinOrderStateHandleService(PayinOrderRepository repository, PayinOrderSupportService supportService, MQMessagePublisher mqMessagePublisher) {
        this.repository = repository;
        this.supportService = supportService;
        this.mqMessagePublisher = mqMessagePublisher;
    }

    public void success(String id, PayinOrderNotifyResult payinOrderNotifyResult) {
        PayinOrder payinOrder = supportService.findById(id);
        boolean success = payinOrder.success(payinOrderNotifyResult);
        if (success) {
            mqMessagePublisher.sendMessageInTransaction(new PayinOrderStateChangeMessage(
                    payinOrder.id(),
                    payinOrder.getInternalOrderId(),
                    payinOrder.getPayAmount(),
                    payinOrder.getState()
            ));
        }
        repository.save(payinOrder);
    }

    public void fail(String id, PayinOrderNotifyResult payinOrderNotifyResult) {
        PayinOrder payinOrderId = supportService.findById(id);
        boolean fail = payinOrderId.fail(payinOrderNotifyResult);
        repository.save(payinOrderId);
        if (fail) {
            mqMessagePublisher.sendMessageInTransaction(new PayinOrderStateChangeMessage(
                    payinOrderId.id(),
                    payinOrderId.getInternalOrderId(),
                    payinOrderId.getPayAmount(),
                    payinOrderId.getState()
            ));
        }
    }

}
