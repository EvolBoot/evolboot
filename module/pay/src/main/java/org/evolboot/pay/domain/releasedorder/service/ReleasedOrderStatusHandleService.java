package org.evolboot.pay.domain.releasedorder.service;

import org.evolboot.mq.producer.RocketMQMessagePublisher;
import org.evolboot.mq.producer.mqtransaction.MqTransactionAppService;
import org.evolboot.pay.domain.releasedorder.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderNotifyResult;
import org.evolboot.shared.event.pay.ReleasedOrderStatusChangeMessage;
import org.evolboot.shared.pay.ReleasedOrderStatus;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author evol
 */
@Service
@Slf4j
public class ReleasedOrderStatusHandleService extends ReleasedOrderSupportService {

    private final RocketMQMessagePublisher rocketMQMessagePublisher;
    private final MqTransactionAppService mqTransactionAppService;

    protected ReleasedOrderStatusHandleService(ReleasedOrderRepository repository, RocketMQMessagePublisher rocketMQMessagePublisher, MqTransactionAppService mqTransactionAppService) {
        super(repository);
        this.rocketMQMessagePublisher = rocketMQMessagePublisher;
        this.mqTransactionAppService = mqTransactionAppService;
    }

    public void success(String id, ReleasedOrderNotifyResult notifyResult) {
        log.info("下发通知:成功:{}", id);
        Optional<ReleasedOrder> optional = repository.findById(id);
        if (optional.isEmpty()) {
            log.info("下发回调查询不到:{}", id);
            return;
        }
        ReleasedOrder releasedOrder = optional.get();
        boolean success = releasedOrder.success(notifyResult);
        if (success) {
            repository.save(releasedOrder);
            rocketMQMessagePublisher.sendMessageInTransaction(new ReleasedOrderStatusChangeMessage(
                    id,
                    releasedOrder.getForeignOrderId(),
                    releasedOrder.getInternalOrderId(),
                    releasedOrder.getAmount(),
                    ReleasedOrderStatus.SUCCESS));
        }
    }

    public void fail(String id, ReleasedOrderNotifyResult notifyResult) {
        log.info("下发通知:失败:{}", id);
        Optional<ReleasedOrder> optional = repository.findById(id);
        if (optional.isEmpty()) {
            log.info("下发回调查询不到:{}", id);
            return;
        }
        ReleasedOrder releasedOrder = optional.get();
        boolean fail = releasedOrder.fail(notifyResult);
        if (fail) {
            repository.save(releasedOrder);
            rocketMQMessagePublisher.sendMessageInTransaction(new ReleasedOrderStatusChangeMessage(
                    id,
                    releasedOrder.getForeignOrderId(),
                    releasedOrder.getInternalOrderId(),
                    releasedOrder.getAmount(),
                    ReleasedOrderStatus.FAIL
            ));
        }
    }
}
