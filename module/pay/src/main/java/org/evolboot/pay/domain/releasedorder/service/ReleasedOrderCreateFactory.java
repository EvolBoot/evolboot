package org.evolboot.pay.domain.releasedorder.service;

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
import org.evolboot.pay.domain.paymentclient.released.ReleasedClient;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.evolboot.pay.exception.PayException;
import org.evolboot.shared.event.pay.ReleasedOrderCreatedMessage;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReleasedOrderOrgType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 代付订单
 *
 * @author evol
 */
@Slf4j
@Service
public class ReleasedOrderCreateFactory  {

    private final Map<PayGateway, ReleasedClient> releasedClients;

    private final PayGatewayAccountQueryService payGatewayAccountQueryService;

    private final ReleasedOrderSupportService supportService;

    private final ReleasedOrderRepository repository;


    private final MQMessagePublisher mqMessagePublisher;

    protected ReleasedOrderCreateFactory(ReleasedOrderRepository repository, Map<PayGateway, ReleasedClient> releasedClients, PayGatewayAccountQueryService payGatewayAccountQueryService, ReleasedOrderSupportService supportService, MQMessagePublisher mqMessagePublisher) {
        this.releasedClients = releasedClients;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
        this.supportService = supportService;
        this.repository = repository;
        this.mqMessagePublisher = mqMessagePublisher;
    }

    public ReleasedOrder execute(Request request) {

        log.info("代付:创建订单:{}", JsonUtil.stringify(request));
        PayGatewayAccount gatewayAccount = payGatewayAccountQueryService.findById(request.getPayGatewayAccountId());
        ReleasedClient releasedClient = releasedClients.get(gatewayAccount.getPayGateway());
        // 支持该代付组织
        if (!releasedClient.supportOrgType(request.getOrgType())) {
            log.info("代付:创建订单:不支持该组织:网关:{},组织:{}", gatewayAccount.getPayGateway(), request.getOrgType());
            throw PayException.dotSupportOrgType(gatewayAccount.getAlias(), request.getOrgType());
        }
        if (!releasedClient.supportCurrency(request.getCurrency())) {
            log.info("代付:创建订单:不支持该货币:网关:{},货币:{}", gatewayAccount.getPayGateway(), request.getCurrency());
            throw PayException.dotSupportCurrency(gatewayAccount.getAlias(), request.getCurrency());
        }
        Assert.notNull(releasedClient, PayI18nMessage.PaymentClient.thePaymentGatewayDoesNotExist());

        ReleasedOrder releasedOrder = new ReleasedOrder(
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
        log.info("代付:网关:{},内部订单号:{},代付订单号:{}", gatewayAccount.getPayGateway(), request.getInternalOrderId(), releasedOrder.id());

        repository.save(releasedOrder);
        mqMessagePublisher.sendMessageInTransaction(new ReleasedOrderCreatedMessage(releasedOrder.id()));
        return releasedOrder;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Request {

        // 内部订单ID
        private String internalOrderId;

        private Currency currency;

        // 金额数量
        private BigDecimal amount;

        // 客户姓名
        private String payeeName;

        private String payeePhone;

        private String payeeEmail;

        // 代付网关
        private Long payGatewayAccountId;

        // 银行代码
        private String bankCode;

        // 银行卡卡号
        private String bankNo;

        //ifsc 编号
        private String ifscCode;

        // ifsc 卡号
        private String ifscCardNo;

        private ReleasedOrderOrgType orgType;

    }

}
