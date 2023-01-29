package org.evolboot.mq.producer.remote;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.shared.event.mq.TestMessage;
import org.evolboot.mq.producer.RocketMQMessagePublisher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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

    private final RocketMQMessagePublisher rocketMQMessagePublisher;

    public AdminMqProducerResourceV1(RocketMQMessagePublisher rocketMQMessagePublisher) {
        this.rocketMQMessagePublisher = rocketMQMessagePublisher;
    }

    @Operation(summary = "发送消息")
    @GetMapping("/send")
    public ResponseModel<?> configurations(
    ) {
        rocketMQMessagePublisher.send(new TestMessage("中午吃啥?"));
        return ResponseModel.ok();
    }


}
