package org.evolboot.pay.domain.payinorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ExtendIllegalArgumentException;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.payin.PayinClient;
import org.evolboot.pay.domain.paymentclient.payin.PayinNotifyRequest;
import org.evolboot.pay.domain.paymentclient.payin.PayinNotifyResponse;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.repository.PayinOrderRepository;
import org.evolboot.shared.event.pay.PayinOrderStateChangeMessage;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.PayinOrderState;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author evol
 */
@Service
@Slf4j
public class PayinOrderNotifyService {


    private final PayinOrderSupportService supportService;

    private final PayinOrderRepository repository;

    private final Map<PayGateway, PayinClient> payinClientMap;

    private final PayGatewayAccountQueryService payGatewayAccountQueryService;

    private final MQMessagePublisher mqMessagePublisher;


    protected PayinOrderNotifyService(PayinOrderSupportService supportService, PayinOrderRepository repository, Map<PayGateway, PayinClient> payinClientMap, PayGatewayAccountQueryService payGatewayAccountQueryService, MQMessagePublisher mqMessagePublisher) {
        this.supportService = supportService;
        this.repository = repository;
        this.payinClientMap = payinClientMap;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
        this.mqMessagePublisher = mqMessagePublisher;
    }


    public <T extends PayinNotifyRequest> Object payinOrderNotify(T request) {
        log.info("代收:收到通知:数据:{}", JsonUtil.stringify(request));
        String payinOrderId = request.getPayinOrderId();
        PayinOrder payinOrder = supportService.findById(payinOrderId);
        PayGatewayAccount payGatewayAccount = payGatewayAccountQueryService.findById(payinOrder.getPayGatewayAccountId());
        PayinClient payinClient = payinClientMap.get(payGatewayAccount.getPayGateway());
        Assert.notNull(payinClient, PayI18nMessage.PaymentClient.thePaymentGatewayDoesNotExist());
        boolean checkSign = request.checkSign(payGatewayAccount);
        if (!checkSign) {
            log.info("代收:通知:签名错误");
            throw new ExtendIllegalArgumentException("Signature error");
        }
        PayinNotifyResponse response = payinClient.payinOrderNotify(payGatewayAccount, request);
        if (PayinOrderState.SUCCESS == response.getState()) {
            log.info("代收:支付成功:{},{}", payGatewayAccount.getPayGateway(), payinOrder.id());
            boolean success = payinOrder.success(response.getNotifyResult());
            if (success) {
                mqMessagePublisher.sendMessageInTransaction(new PayinOrderStateChangeMessage(
                        payinOrder.id(),
                        payinOrder.getInternalOrderId(),
                        payinOrder.getPayAmount(),
                        payinOrder.getState()
                ));
            }
        } else if (PayinOrderState.FAIL == response.getState()) {
            log.info("代收:支付失败:{},{}", payGatewayAccount.getPayGateway(), payinOrder.id());
            boolean fail = payinOrder.fail(response.getNotifyResult());
            if (fail) {
                mqMessagePublisher.sendMessageInTransaction(new PayinOrderStateChangeMessage(
                        payinOrder.id(),
                        payinOrder.getInternalOrderId(),
                        payinOrder.getPayAmount(),
                        payinOrder.getState()
                ));
            }
        }
        repository.save(payinOrder);
        return response.getReturnText();
    }

}
