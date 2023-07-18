package org.evolboot.pay.remote.paymentclient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paymentclient.gateway.qart.QartConfig;
import org.evolboot.pay.domain.paymentclient.gateway.qart.receipt.QartReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.gateway.qart.receipt.QartReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.paymentclient.gateway.qart.released.QartReleasedNotifyRequest;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderAppService;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderAppService;
import org.evolboot.pay.remote.paymentclient.util.RequestUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeMap;

/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/payment/qart")
@Tag(name = "qart通道", description = "qart通道")
@ApiClient
public class QartPaymentClientResourceV1 {


    private final ReleasedOrderAppService releasedOrderAppService;

    private final ReceiptOrderAppService receiptOrderAppService;

    private final QartConfig config;

    public QartPaymentClientResourceV1(ReleasedOrderAppService releasedOrderAppService, ReceiptOrderAppService receiptOrderAppService, QartConfig config) {
        this.releasedOrderAppService = releasedOrderAppService;
        this.receiptOrderAppService = receiptOrderAppService;
        this.config = config;
    }


    @Operation(summary = "代收通知")
    @PostMapping(
            value = {"/receipt/notify"},
            consumes = {"application/x-www-form-urlencoded"}
    )
    public String receiptNotify(HttpServletRequest request) {
        TreeMap<String, Object> requestParams = new TreeMap<>();
        request.getParameterNames().asIterator().forEachRemaining(key -> requestParams.put(key, request.getParameter(key)));
        log.info("Qart:代收:收到:{}", JsonUtil.stringify(requestParams));
        return (String) receiptOrderAppService.receiptOrderNotify(new QartReceiptNotifyRequest(requestParams));
    }


    @Operation(summary = "代付通知")
    @PostMapping(
            value = {"/released/notify"},
            consumes = {"application/json"}
    )
    public String releasedNotify(HttpServletRequest request) {
        String requestParams = RequestUtil.convertParamsToString(request);
        log.info("Qart:代付:收到:{}", JsonUtil.stringify(requestParams));
        QartReleasedNotifyRequest notifyRequest = JsonUtil.parse(requestParams, QartReleasedNotifyRequest.class);
        notifyRequest.setNotifyParamsText(requestParams);
        notifyRequest.setQartConfig(config);
        return (String) releasedOrderAppService.releasedOrderNotify(notifyRequest);
    }


    @Operation(summary = "前台回调")
    @GetMapping("/redirect/{id}/{state}")
    public void redirect(
            @PathVariable("id") String id,
            @PathVariable("state") String state,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        TreeMap<String, Object> requestParams = new TreeMap<>();
        request.getParameterNames().asIterator().forEachRemaining(key -> requestParams.put(key, request.getParameter(key)));
        log.info("代付:前台回调:Qart:{}", JsonUtil.stringify(requestParams));
        response.sendRedirect(receiptOrderAppService.getReceiptRedirectUrl(new QartReceiptRedirectNotifyRequest(id, state)));
    }


}
