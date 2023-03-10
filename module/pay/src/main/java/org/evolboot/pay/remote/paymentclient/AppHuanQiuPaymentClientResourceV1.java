package org.evolboot.pay.remote.paymentclient;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paymentclient.PaymentClientAppService;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.receipt.HuanQiuPayReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.receipt.HuanQiuPayReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.released.HuanQiuPayReleasedNotifyRequest;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.pay.ReceiptOrderStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/pay/payment/huan-qiu-pay")
@Tag(name = "第三方支付管理", description = "第三方支付管理")
@ApiClient
public class AppHuanQiuPaymentClientResourceV1 {

    private final PaymentClientAppService paymentClientAppService;

    public AppHuanQiuPaymentClientResourceV1(PaymentClientAppService paymentClientAppService) {
        this.paymentClientAppService = paymentClientAppService;
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
        return (String) paymentClientAppService.receiptOrderNotify(new HuanQiuPayReceiptNotifyRequest(requestParams));
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
        return (String) paymentClientAppService.releasedOrderNotify(new HuanQiuPayReleasedNotifyRequest(requestParams));
    }


    @Operation(summary = "同步返回SUCCESS")
    @PostMapping(
            value = {"/success"},
            consumes = {"multipart/form-data"}
    )
    public void huanQiuPaySuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(paymentClientAppService.getReceiptRedirectUrl(new HuanQiuPayReceiptRedirectNotifyRequest("111111")));
    }


    @Operation(summary = "同步返回FAIL")
    @PostMapping(
            value = {"/fail"},
            consumes = {"multipart/form-data"}
    )
    public void huanQiuPayfail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(paymentClientAppService.getReceiptRedirectUrl(new HuanQiuPayReceiptRedirectNotifyRequest("111111")));

    }


}
