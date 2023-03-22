package org.evolboot.mq.core.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.exception.ExtendRuntimeException;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.shared.event.mq.TestMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/mq-producer")
@Tag(name = "消息队列生产商", description = "消息队列生产商")
@ApiClient
public class AppMqProducerResourceV1 {

    private final MQMessagePublisher mqMessagePublisher;

    public AppMqProducerResourceV1(MQMessagePublisher mqMessagePublisher) {
        this.mqMessagePublisher = mqMessagePublisher;
    }

    @Operation(summary = "发送消息")
    @GetMapping("/send")
    public ResponseModel<?> configurations(
    ) {
        mqMessagePublisher.send(new TestMessage("中午吃啥?"));
        return ResponseModel.ok();
    }


}
