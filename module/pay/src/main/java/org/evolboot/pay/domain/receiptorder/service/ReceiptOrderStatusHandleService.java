package org.evolboot.pay.domain.receiptorder.service;

import org.evolboot.mq.producer.RocketMQMessagePublisher;
import org.evolboot.mq.producer.mqtransaction.MqTransactionAppService;
import org.evolboot.pay.domain.receiptorder.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderNotifyResult;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.event.pay.ReceiptOrderStatusChangeMessage;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class ReceiptOrderStatusHandleService extends ReceiptOrderSupportService {

    private final RocketMQMessagePublisher mqMessagePublisher;
    private final MqTransactionAppService mqTransactionAppService;

    protected ReceiptOrderStatusHandleService(ReceiptOrderRepository repository, RocketMQMessagePublisher mqMessagePublisher, MqTransactionAppService mqTransactionAppService) {
        super(repository);
        this.mqMessagePublisher = mqMessagePublisher;
        this.mqTransactionAppService = mqTransactionAppService;
    }

    public void success(String id, ReceiptOrderNotifyResult receiptOrderNotifyResult) {
        ReceiptOrder receiptOrder = findById(id);
        boolean success = receiptOrder.success(receiptOrderNotifyResult);
        if (success) {
            mqMessagePublisher.sendMessageInTransaction(new ReceiptOrderStatusChangeMessage(
                    receiptOrder.id(),
                    receiptOrder.getInternalOrderId(),
                    receiptOrder.getPayAmount(),
                    receiptOrder.getStatus()
            ));
        }
        repository.save(receiptOrder);
    }

    public void fail(String id, ReceiptOrderNotifyResult receiptOrderNotifyResult) {
        ReceiptOrder receiptOrder = findById(id);
        boolean fail = receiptOrder.fail(receiptOrderNotifyResult);
        repository.save(receiptOrder);
        if (fail) {
            mqMessagePublisher.sendMessageInTransaction(new ReceiptOrderStatusChangeMessage(
                    receiptOrder.id(),
                    receiptOrder.getInternalOrderId(),
                    receiptOrder.getPayAmount(),
                    receiptOrder.getStatus()
            ));
        }
    }

}
