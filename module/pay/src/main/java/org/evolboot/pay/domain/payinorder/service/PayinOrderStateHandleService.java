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

    public void success(String id, PayinOrderNotifyResult receiptOrderNotifyResult) {
        PayinOrder receiptOrder = supportService.findById(id);
        boolean success = receiptOrder.success(receiptOrderNotifyResult);
        if (success) {
            mqMessagePublisher.sendMessageInTransaction(new PayinOrderStateChangeMessage(
                    receiptOrder.id(),
                    receiptOrder.getInternalOrderId(),
                    receiptOrder.getPayAmount(),
                    receiptOrder.getState()
            ));
        }
        repository.save(receiptOrder);
    }

    public void fail(String id, PayinOrderNotifyResult receiptOrderNotifyResult) {
        PayinOrder receiptOrder = supportService.findById(id);
        boolean fail = receiptOrder.fail(receiptOrderNotifyResult);
        repository.save(receiptOrder);
        if (fail) {
            mqMessagePublisher.sendMessageInTransaction(new PayinOrderStateChangeMessage(
                    receiptOrder.id(),
                    receiptOrder.getInternalOrderId(),
                    receiptOrder.getPayAmount(),
                    receiptOrder.getState()
            ));
        }
    }

}
