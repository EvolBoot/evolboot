package org.evolboot.pay.remote.paymentclient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.shared.event.pay.ReceiptOrderStateChangeMessage;
import org.evolboot.shared.event.pay.ReleasedOrderStateChangeMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/payment/test")
@Tag(name = "测试", description = "测试")
@ApiClient
public class TestPaymentClientResourceV1 {


    private final MQMessagePublisher mqMessagePublisher;

    public TestPaymentClientResourceV1(MQMessagePublisher mqMessagePublisher) {
        this.mqMessagePublisher = mqMessagePublisher;
    }


    @Operation(summary = "发送代收订单状态消息")
    @PostMapping("/send-receipt-order-state-change-message")
    @Transactional
    public String sendMessageInTransactionReceiptOrderStateChangeMessage(ReceiptOrderStateChangeMessage request) {
        mqMessagePublisher.sendMessageInTransaction(request);
        return "success";
    }


    @Operation(summary = "发送代付订单状态消息")
    @PostMapping("/send-released-order-state-change-message")
    @Transactional
    public String sendMessageInTransactionReleasedOrderStateChangeMessage(ReleasedOrderStateChangeMessage request) {
        mqMessagePublisher.sendMessageInTransaction(request);
        return "success";
    }


    /**
     * @param request
     * @return
     * @throws InterruptedException
     */
    @Operation(summary = "测试通知1")
    @PostMapping("/evolboot-notify")
    public String evolbootNotify(HttpServletRequest request) throws InterruptedException {
        log.info("evolbootNotify");
        Enumeration<String> parameterNames = request.getParameterNames();
        Iterator<String> stringIterator = parameterNames.asIterator();
        while (stringIterator.hasNext()) {
            String key = stringIterator.next();
            String value = request.getParameter(key);
            log.info("key:{},value:{}", key, value);
        }
        return "ok";
    }


    @Operation(summary = "测试通知2")
    @PostMapping("/evolboot-notify-standby")
    public String evolbootNotifyStandby(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        Iterator<String> stringIterator = parameterNames.asIterator();
        while (stringIterator.hasNext()) {
            String key = stringIterator.next();
            String value = request.getParameter(key);
            log.info("key:{},value:{}", key, value);
        }
        return "ok";
    }


}
