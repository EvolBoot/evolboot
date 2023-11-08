package org.evolboot.pay.domain.receiptorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrderNotifyResult;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.shared.event.pay.ReceiptOrderStateChangeMessage;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class ReceiptOrderStateHandleService {

    private final ReceiptOrderRepository repository;

    private final ReceiptOrderSupportService supportService;


    private final MQMessagePublisher mqMessagePublisher;

    protected ReceiptOrderStateHandleService(ReceiptOrderRepository repository, ReceiptOrderSupportService supportService, MQMessagePublisher mqMessagePublisher) {
        this.repository = repository;
        this.supportService = supportService;
        this.mqMessagePublisher = mqMessagePublisher;
    }

    public void success(String id, ReceiptOrderNotifyResult receiptOrderNotifyResult) {
        ReceiptOrder receiptOrder = supportService.findById(id);
        boolean success = receiptOrder.success(receiptOrderNotifyResult);
        if (success) {
            mqMessagePublisher.sendMessageInTransaction(new ReceiptOrderStateChangeMessage(
                    receiptOrder.id(),
                    receiptOrder.getInternalOrderId(),
                    receiptOrder.getPayAmount(),
                    receiptOrder.getState()
            ));
        }
        repository.save(receiptOrder);
    }

    public void fail(String id, ReceiptOrderNotifyResult receiptOrderNotifyResult) {
        ReceiptOrder receiptOrder = supportService.findById(id);
        boolean fail = receiptOrder.fail(receiptOrderNotifyResult);
        repository.save(receiptOrder);
        if (fail) {
            mqMessagePublisher.sendMessageInTransaction(new ReceiptOrderStateChangeMessage(
                    receiptOrder.id(),
                    receiptOrder.getInternalOrderId(),
                    receiptOrder.getPayAmount(),
                    receiptOrder.getState()
            ));
        }
    }

}
