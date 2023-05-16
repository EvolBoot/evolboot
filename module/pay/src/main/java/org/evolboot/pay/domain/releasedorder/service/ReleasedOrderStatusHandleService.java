package org.evolboot.pay.domain.releasedorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.pay.domain.releasedorder.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.evolboot.shared.event.pay.ReleasedOrderStatusChangeMessage;
import org.evolboot.shared.pay.ReleasedOrderStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author evol
 */
@Service
@Slf4j
public class ReleasedOrderStatusHandleService extends ReleasedOrderSupportService {

    private final MQMessagePublisher mqMessagePublisher;

    protected ReleasedOrderStatusHandleService(ReleasedOrderRepository repository, MQMessagePublisher mqMessagePublisher) {
        super(repository);
        this.mqMessagePublisher = mqMessagePublisher;
    }


    public void success(ReleasedOrder releasedOrder) {
        log.info("代付:成功:{}", releasedOrder.id());
        boolean success = releasedOrder.success();
        if (success) {
            mqMessagePublisher.sendMessageInTransaction(new ReleasedOrderStatusChangeMessage(
                    releasedOrder.id(),
                    releasedOrder.getForeignOrderId(),
                    releasedOrder.getInternalOrderId(),
                    releasedOrder.getAmount(),
                    ReleasedOrderStatus.SUCCESS));
        }
        repository.save(releasedOrder);
    }

    public void fail(ReleasedOrder releasedOrder) {
        log.info("代付:失败:{}", releasedOrder.id());
        boolean fail = releasedOrder.fail();
        if (fail) {
            mqMessagePublisher.sendMessageInTransaction(new ReleasedOrderStatusChangeMessage(
                    releasedOrder.id(),
                    releasedOrder.getForeignOrderId(),
                    releasedOrder.getInternalOrderId(),
                    releasedOrder.getAmount(),
                    ReleasedOrderStatus.FAIL
            ));
        }
        repository.save(releasedOrder);
    }


    public void pending(ReleasedOrder releasedOrder, BigDecimal poundage,
                        String foreignOrderId) {
        log.info("代付:Pending:{}", releasedOrder.id());
        releasedOrder.pending(poundage, foreignOrderId);
        repository.save(releasedOrder);

    }
}
