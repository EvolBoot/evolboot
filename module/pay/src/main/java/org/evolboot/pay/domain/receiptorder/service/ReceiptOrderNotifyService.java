package org.evolboot.pay.domain.receiptorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ExtendIllegalArgumentException;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptClient;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyResponse;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.shared.event.pay.ReceiptOrderStateChangeMessage;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReceiptOrderState;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author evol
 */
@Service
@Slf4j
public class ReceiptOrderNotifyService {


    private final ReceiptOrderSupportService supportService;

    private final ReceiptOrderRepository repository;

    private final Map<PayGateway, ReceiptClient> receiptClients;

    private final PayGatewayAccountQueryService payGatewayAccountQueryService;

    private final MQMessagePublisher mqMessagePublisher;


    protected ReceiptOrderNotifyService(ReceiptOrderSupportService supportService, ReceiptOrderRepository repository, Map<PayGateway, ReceiptClient> receiptClients, PayGatewayAccountQueryService payGatewayAccountQueryService, MQMessagePublisher mqMessagePublisher) {
        this.supportService = supportService;
        this.repository = repository;
        this.receiptClients = receiptClients;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
        this.mqMessagePublisher = mqMessagePublisher;
    }


    public <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T request) {
        log.info("代收:收到通知:数据:{}", JsonUtil.stringify(request));
        String receiptOrderId = request.getReceiptOrderId();
        ReceiptOrder receiptOrder = supportService.findById(receiptOrderId);
        PayGatewayAccount payGatewayAccount = payGatewayAccountQueryService.findById(receiptOrder.getPayGatewayAccountId());
        ReceiptClient receiptClient = receiptClients.get(payGatewayAccount.getPayGateway());
        Assert.notNull(receiptClient, PayI18nMessage.PaymentClient.thePaymentGatewayDoesNotExist());
        boolean checkSign = request.checkSign(payGatewayAccount);
        if (!checkSign) {
            log.info("代收:通知:签名错误");
            throw new ExtendIllegalArgumentException("Signature error");
        }
        ReceiptNotifyResponse response = receiptClient.receiptOrderNotify(payGatewayAccount, request);
        if (ReceiptOrderState.SUCCESS == response.getState()) {
            log.info("代收:支付成功:{},{}", payGatewayAccount.getPayGateway(), receiptOrder.id());
            boolean success = receiptOrder.success(response.getNotifyResult());
            if (success) {
                mqMessagePublisher.sendMessageInTransaction(new ReceiptOrderStateChangeMessage(
                        receiptOrder.id(),
                        receiptOrder.getInternalOrderId(),
                        receiptOrder.getPayAmount(),
                        receiptOrder.getState()
                ));
            }
        } else if (ReceiptOrderState.FAIL == response.getState()) {
            log.info("代收:支付失败:{},{}", payGatewayAccount.getPayGateway(), receiptOrder.id());
            boolean fail = receiptOrder.fail(response.getNotifyResult());
            if (fail) {
                mqMessagePublisher.sendMessageInTransaction(new ReceiptOrderStateChangeMessage(
                        receiptOrder.id(),
                        receiptOrder.getInternalOrderId(),
                        receiptOrder.getPayAmount(),
                        receiptOrder.getState()
                ));
            }
        }
        repository.save(receiptOrder);
        return response.getReturnText();
    }

}