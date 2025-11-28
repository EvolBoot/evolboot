package org.evolboot.pay.domain.payoutorder.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.payout.PayoutClient;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.evolboot.pay.exception.PayException;
import org.evolboot.shared.event.pay.PayoutOrderCreatedMessage;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.PayoutOrderOrgType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/** 代付订单创建工厂 */
@Slf4j
@Service
public class PayoutOrderCreateFactory {

    private final Map<PayGateway, PayoutClient> payoutClientMap;
    private final PayGatewayAccountQueryService payGatewayAccountQueryService;
    private final PayoutOrderSupportService supportService;
    private final PayoutOrderRepository repository;
    private final MQMessagePublisher mqMessagePublisher;

    protected PayoutOrderCreateFactory(PayoutOrderRepository repository,
                                       Map<PayGateway, PayoutClient> payoutClientMap,
                                       PayGatewayAccountQueryService payGatewayAccountQueryService,
                                       PayoutOrderSupportService supportService,
                                       MQMessagePublisher mqMessagePublisher) {
        this.payoutClientMap = payoutClientMap;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
        this.supportService = supportService;
        this.repository = repository;
        this.mqMessagePublisher = mqMessagePublisher;
    }

    public PayoutOrder execute(Request request) {
        log.info("代付:创建订单:{}", JsonUtil.stringify(request));
        PayGatewayAccount gatewayAccount = payGatewayAccountQueryService.findById(request.getPayGatewayAccountId());
        PayoutClient payoutClient = payoutClientMap.get(gatewayAccount.getPayGateway());
        // 支持该代付组织
        if (!payoutClient.supportOrgType(request.getOrgType())) {
            log.info("代付:创建订单:不支持该组织:网关:{},组织:{}", gatewayAccount.getPayGateway(), request.getOrgType());
            throw PayException.dotSupportOrgType(gatewayAccount.getAlias(), request.getOrgType());
        }

        if (!gatewayAccount.supportCurrency(request.getCurrency())) {
            log.info("代付:创建订单:不支持该货币:网关:{},货币:{}", gatewayAccount.getPayGateway(), request.getCurrency());
            throw PayException.dotSupportCurrency(gatewayAccount.getAlias(), request.getCurrency());
        }

        if (!gatewayAccount.validatePayoutAmount(request.getCurrency(), request.getAmount())) {
            log.info("代付:创建订单:金额不在范围内:网关:{},货币:{},金额:{}", gatewayAccount.getPayGateway(), request.getCurrency(), request.getAmount());
            throw PayException.PAYOUT_AMOUNT_OUT_OF_RANGE;
        }

        Assert.notNull(payoutClient, PayI18nMessage.PaymentClient.thePaymentGatewayDoesNotExist());

        PayoutOrder payoutOrder = new PayoutOrder(
                request.internalOrderId,
                request.currency,
                request.amount,
                request.payeeName,
                request.payeePhone,
                request.payeeEmail,
                request.bankCode,
                request.bankNo,
                request.ifscCode,
                request.ifscCardNo,
                request.orgType,
                request.payGatewayAccountId,
                gatewayAccount.getPayGateway()
        );
        log.info("代付:网关:{},内部订单号:{},代付订单号:{}", gatewayAccount.getPayGateway(), request.getInternalOrderId(), payoutOrder.id());

        repository.save(payoutOrder);
        mqMessagePublisher.sendMessageInTransaction(new PayoutOrderCreatedMessage(payoutOrder.id()));
        return payoutOrder;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String internalOrderId; // 内部订单ID
        private Currency currency;
        private BigDecimal amount;     // 金额数量
        private String payeeName;      // 客户姓名
        private String payeePhone;
        private String payeeEmail;
        private Long payGatewayAccountId; // 代付网关
        private String bankCode;       // 银行代码
        private String bankNo;         // 银行卡卡号
        private String ifscCode;       // ifsc 编号
        private String ifscCardNo;     // ifsc 卡号
        private PayoutOrderOrgType orgType;
    }
}
