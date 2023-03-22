package org.evolboot.mq.core.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.mq.DelayLevel;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.shared.event.mq.TestMessage;
import org.evolboot.shared.event.mq.TestTransactionMQMessage;
import org.evolboot.shared.event.mq.TransactionMQMessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/mq-producer")
@Tag(name = "消息队列生产商", description = "消息队列生产商")
@AdminClient
public class AdminMqProducerResourceV1 {

    private final MQMessagePublisher messagePublisher;

    public AdminMqProducerResourceV1(MQMessagePublisher MQMessagePublisher) {
        this.messagePublisher = MQMessagePublisher;
    }

    @Operation(summary = "发送消息")
    @GetMapping("/send")
    public ResponseModel<?> send(
    ) {
        messagePublisher.send(new TestMessage("中午吃啥?"));
        return ResponseModel.ok();
    }


    @Operation(summary = "发送事务消息")
    @GetMapping("/send-tran")
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseModel<?> sendTran(
    ) {
        messagePublisher.sendMessageInTransaction(new TestTransactionMQMessage());
        return ResponseModel.ok();
    }


    @Operation(summary = "发送消息延时消息")
    @GetMapping("/send-delay-time")
    public ResponseModel<?> setDelayTime(
            String message,
            DelayLevel delayLevel
    ) {
        messagePublisher.send(new TestMessage(message), delayLevel);
        return ResponseModel.ok();
    }


}
