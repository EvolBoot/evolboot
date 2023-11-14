package org.evolboot.pay.remote.paymentclient;

import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.receipt.HuanQiuPayReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.released.HuanQiuPayReleasedNotifyRequest;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderAppService;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderAppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/payment/huan-qiu-pay")
@Tag(name = "第三方支付管理", description = "第三方支付管理")
@ApiClient
public class AppHuanQiuPaymentClientResourceV1 {

    private final ReleasedOrderAppService releasedOrderAppService;

    private final ReceiptOrderAppService receiptOrderAppService;


    public AppHuanQiuPaymentClientResourceV1(ReleasedOrderAppService releasedOrderAppService, ReceiptOrderAppService receiptOrderAppService) {
        this.releasedOrderAppService = releasedOrderAppService;
        this.receiptOrderAppService = receiptOrderAppService;
    }


    @Operation(summary = "环球回调")
    @PostMapping(
            value = {"/receipt/notify"},
            consumes = {"multipart/form-data"}
    )
    public String huanQiuPayNotify(HttpServletRequest request) {
        Map<String, String> requestParams = Maps.newHashMap();
        request.getParameterNames().asIterator().forEachRemaining(key -> requestParams.put(key, request.getParameter(key)));
        log.info("环球代收回调:收到:{}", JsonUtil.stringify(requestParams));
        return (String) receiptOrderAppService.receiptOrderNotify(new HuanQiuPayReceiptNotifyRequest(requestParams));
    }


    @Operation(summary = "环球回调测试")
    @PostMapping(
            value = {"/notify-test"},
            consumes = {"multipart/form-data"}
    )
    public String huanQiuPayNotifyTest(HttpServletRequest request) {
        Map<String, String> requestParams = Maps.newHashMap();
        request.getParameterNames().asIterator().forEachRemaining(key -> requestParams.put(key, request.getParameter(key)));
        log.info("环球测试回调:收到:{}", JsonUtil.stringify(requestParams));
        return "success";
    }


    @Operation(summary = "环球下发回调")
    @PostMapping(
            value = {"/released/notify"},
            consumes = {"multipart/form-data"}
    )
    public String huanQiuPayReleasedNotify(HttpServletRequest request) {
        Map<String, String> requestParams = Maps.newHashMap();
        request.getParameterNames().asIterator().forEachRemaining(key -> requestParams.put(key, request.getParameter(key)));
        log.info("环球下发回调:收到:{}", JsonUtil.stringify(requestParams));
        return (String) releasedOrderAppService.releasedOrderNotify(new HuanQiuPayReleasedNotifyRequest(requestParams));
    }


    @Operation(summary = "同步返回SUCCESS")
    @PostMapping(
            value = {"/success"},
            consumes = {"multipart/form-data"}
    )
    public String huanQiuPaySuccess(HttpServletRequest request) {

        return "success";
    }


    @Operation(summary = "同步返回FAIL")
    @PostMapping(
            value = {"/fail"},
            consumes = {"multipart/form-data"}
    )
    public String huanQiuPayfail(HttpServletRequest request) {
        return "fail";
    }


}
