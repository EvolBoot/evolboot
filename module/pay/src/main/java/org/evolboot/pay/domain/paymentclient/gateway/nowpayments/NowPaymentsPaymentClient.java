package org.evolboot.pay.domain.paymentclient.gateway.nowpayments;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendHttpUtil;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.gateway.nowpayments.receipt.NowPaymentsCreatePaymentRequest;
import org.evolboot.pay.domain.paymentclient.gateway.nowpayments.receipt.NowPaymentsCreatePaymentResponse;
import org.evolboot.pay.domain.paymentclient.gateway.nowpayments.receipt.NowPaymentsPaymentStatusResponse;
import org.evolboot.pay.domain.paymentclient.gateway.nowpayments.receipt.NowPaymentsReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.*;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrderNotifyResult;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrderRequestResult;
import org.evolboot.pay.exception.PayException;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * NOWPayments 虚拟币支付客户端
 *
 * @author evol
 */
@Slf4j
@Service("NowPaymentsPaymentClient")
public class NowPaymentsPaymentClient implements ReceiptClient {

    private final NowPaymentsConfig config;

    public NowPaymentsPaymentClient(NowPaymentsConfig config) {
        this.config = config;
    }

    @Override
    public PayGateway getPayGateway() {
        return PayGateway.NOW_PAYMENTS;
    }

    @Override
    public boolean supportCurrency(Currency currency) {
        try {
            NowPaymentsUtil.convertCurrency(currency);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public ReceiptCreateResponse createReceiptOrder(
        String receiptOrderId,
        PayGatewayAccount account,
        ReceiptCreateRequest request
    ) {
        log.info("NOWPayments: 创建虚拟币支付订单: {}", receiptOrderId);

        try {
            // 1. 准备请求参数
            String payCurrency = NowPaymentsUtil.convertCurrency(request.getCurrency());
            NowPaymentsCreatePaymentRequest apiRequest = new NowPaymentsCreatePaymentRequest(
                request.getPayAmount(),
                NowPaymentsUtil.getPriceCurrency(),
                payCurrency,
                config.getIpnCallbackUrl(),
                receiptOrderId,
                request.getProductName() != null ? request.getProductName() : "Payment"
            );

            log.info("NOWPayments: 请求参数: {}", JsonUtil.stringify(apiRequest));

            // 2. 调用 NOWPayments API
            Map<String, String> headers = Maps.newHashMap();
            headers.put("x-api-key", config.getApiKey());

            // 将请求对象转为 Map
            @SuppressWarnings("unchecked")
            Map<String, Object> requestMap = JsonUtil.parse(
                JsonUtil.stringify(apiRequest),
                Map.class
            );

            String responseJson = ExtendHttpUtil.postJson(
                config.getCreatePaymentUrl(),
                requestMap,
                headers
            );

            log.info("NOWPayments: 创建订单响应: {}", responseJson);

            // 3. 解析响应
            NowPaymentsCreatePaymentResponse apiResponse = JsonUtil.parse(
                responseJson,
                NowPaymentsCreatePaymentResponse.class
            );

            if (!apiResponse.isSuccess()) {
                log.error("NOWPayments: 创建订单失败: {}", responseJson);
                throw PayException.RECEIPT_ORDER_ERROR;
            }

            // 4. 构建返回结果
            return new ReceiptCreateResponse(
                true,
                receiptOrderId,
                apiResponse.getPayAddress(),
                new ReceiptOrderRequestResult(
                    apiResponse.getPaymentId(),
                    apiResponse.getPayAddress(),
                    apiResponse.getPaymentStatus(),
                    responseJson
                )
            );

        } catch (Exception e) {
            log.error("NOWPayments: 创建订单异常: orderId={}", receiptOrderId, e);
            throw PayException.RECEIPT_ORDER_ERROR;
        }
    }

    @Override
    public <T extends ReceiptNotifyRequest> ReceiptNotifyResponse receiptOrderNotify(
        PayGatewayAccount gatewayAccount,
        T request
    ) {
        log.info("NOWPayments: 处理 IPN 回调通知");

        if (!(request instanceof NowPaymentsReceiptNotifyRequest)) {
            log.error("NOWPayments: 无效的通知请求类型");
            throw new IllegalArgumentException("Invalid notify request type");
        }

        NowPaymentsReceiptNotifyRequest nowRequest = (NowPaymentsReceiptNotifyRequest) request;

        // 注意: 签名验证应该在 Controller 层完成,这里直接处理业务逻辑
        log.info("NOWPayments: IPN 通知内容: paymentId={}, status={}, orderId={}",
            nowRequest.getPaymentId(),
            nowRequest.getPaymentStatus(),
            nowRequest.getOrderId()
        );

        return new ReceiptNotifyResponse(
            nowRequest.getState(),
            "OK",
            new ReceiptOrderNotifyResult(
                nowRequest.getForeignOrderId(),
                nowRequest.getReceiptOrderId(),
                nowRequest.getForeignState(),
                nowRequest.getNotifyParamsText(),
                nowRequest.getPayAmount(),
                nowRequest.getRealPayAmount(),
                nowRequest.getPoundage()
            )
        );
    }

    @Override
    public <T extends ReceiptRedirectNotifyRequest> ReceiptRedirectNotifyResponse receiptOrderRedirectNotify(
        PayGatewayAccount gatewayAccount,
        T request
    ) {
        // NOWPayments 不需要前端同步回调,虚拟币支付通过 IPN 异步通知即可
        log.info("NOWPayments: 不支持前端同步回调");
        return new ReceiptRedirectNotifyResponse(request.getState());
    }

    /**
     * 主动查询支付状态(可用于补偿机制)
     */
    public NowPaymentsPaymentStatusResponse queryPaymentStatus(String paymentId) {
        log.info("NOWPayments: 查询支付状态: paymentId={}", paymentId);

        try {
            Map<String, Object> headers = Maps.newHashMap();
            headers.put("x-api-key", config.getApiKey());

            String responseJson = ExtendHttpUtil.get(
                config.getPaymentStatusUrl(paymentId),
                Maps.newHashMap(),
                (Map<String, String>) (Map) headers
            );

            log.info("NOWPayments: 查询状态响应: {}", responseJson);

            return JsonUtil.parse(responseJson, NowPaymentsPaymentStatusResponse.class);

        } catch (Exception e) {
            log.error("NOWPayments: 查询支付状态异常: paymentId={}", paymentId, e);
            throw new RuntimeException("查询支付状态失败", e);
        }
    }
}
