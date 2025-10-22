package org.evolboot.pay.remote.paymentclient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paymentclient.gateway.nowpayments.payin.NowPaymentsPayinNotifyRequest;
import org.evolboot.pay.domain.payinorder.PayinOrderAppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NOWPayments 支付回调接口
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/payment/now-payments")
@Tag(name = "NOWPayments 虚拟币支付", description = "NOWPayments 虚拟币支付回调接口")
@ApiClient
public class AppNowPaymentsClientResourceV1 {

    private final PayinOrderAppService payinOrderAppService;

    public AppNowPaymentsClientResourceV1(PayinOrderAppService payinOrderAppService) {
        this.payinOrderAppService = payinOrderAppService;
    }

    /**
     * NOWPayments IPN 回调通知
     *
     * NOWPayments 会以 JSON 格式发送 POST 请求到此接口
     * Header 中包含签名: x-nowpayments-sig
     *
     * @param signature 签名值(来自 header)
     * @param body      原始请求体(JSON 字符串)
     * @return "OK" 表示接收成功
     */
    @Operation(summary = "NOWPayments IPN 回调通知")
    @PostMapping(
        value = "/payin/notify",
        consumes = "application/json",
        produces = "text/plain"
    )
    public String nowPaymentsIpnNotify(
        @RequestHeader(value = "x-nowpayments-sig", required = false) String signature,
        @RequestBody String body
    ) {
        log.info("NOWPayments: 收到 IPN 回调通知");
        log.info("NOWPayments: 签名: {}", signature);
        log.info("NOWPayments: 内容: {}", body);

        try {
            // 解析 JSON 请求体
            NowPaymentsPayinNotifyRequest request = JsonUtil.parse(
                body,
                NowPaymentsPayinNotifyRequest.class
            );

            // 设置原始文本和签名(用于验证)
            request.setNotifyParamsText(body);
            request.setIpnSignature(signature);

            // 调用应用服务处理通知
            Object result = payinOrderAppService.payinOrderNotify(request);

            log.info("NOWPayments: IPN 通知处理完成, 订单: {}, 状态: {}",
                request.getOrderId(),
                request.getPaymentStatus()
            );

            // NOWPayments 期望返回 "OK" 表示成功接收
            return result != null ? result.toString() : "OK";

        } catch (Exception e) {
            log.error("NOWPayments: IPN 回调处理异常", e);
            // 即使处理失败也返回 OK,避免 NOWPayments 重复发送
            // 具体错误应该通过监控告警处理
            return "OK";
        }
    }

    /**
     * NOWPayments IPN 回调测试接口
     *
     * 用于开发调试,仅记录日志不做实际处理
     */
    @Operation(summary = "NOWPayments IPN 回调测试")
    @PostMapping(
        value = "/payin/notify-test",
        consumes = "application/json",
        produces = "text/plain"
    )
    public String nowPaymentsIpnNotifyTest(
        @RequestHeader(value = "x-nowpayments-sig", required = false) String signature,
        @RequestBody String body
    ) {
        log.info("NOWPayments 测试回调: 签名={}", signature);
        log.info("NOWPayments 测试回调: 内容={}", body);
        return "OK";
    }
}
