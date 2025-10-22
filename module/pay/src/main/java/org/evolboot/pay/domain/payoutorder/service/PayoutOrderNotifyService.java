package org.evolboot.pay.domain.payoutorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ExtendIllegalArgumentException;
import org.evolboot.core.util.Assert;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.payout.PayoutClient;
import org.evolboot.pay.domain.paymentclient.payout.PayoutNotifyRequest;
import org.evolboot.pay.domain.paymentclient.payout.PayoutNotifyResponse;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.PayoutOrderState;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.evolboot.pay.PayI18nMessage.PaymentClient.thePaymentGatewayDoesNotExist;

@Service
@Slf4j
public class PayoutOrderNotifyService {

    private final Map<PayGateway, PayoutClient> payoutClientMap;
    private final PayGatewayAccountQueryService payGatewayAccountQueryService;
    private final PayoutOrderStateHandleService stateHaeeendleService;
    private final PayoutOrderSupportService supportService;
    private final PayoutOrderRepository repository;

    protected PayoutOrderNotifyService(PayoutOrderRepository repository,
                                       Map<PayGateway, PayoutClient> payoutClientMap,
                                       PayGatewayAccountQueryService payGatewayAccountQueryService,
                                       PayoutOrderStateHandleService stateHandleService,
                                       PayoutOrderSupportService supportService) {
        this.repository = repository;
        this.payoutClientMap = payoutClientMap;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
        this.stateHaeeendleService = stateHandleService;
        this.supportService = supportService;
    }

    public <T extends PayoutNotifyRequest> Object payoutOrderNotify(T request) {
        log.info("代付:收到回调通知:{}", request.getNotifyParamsText());
        PayoutOrder payoutOrder = supportService.findById(request.getPayoutOrderId());
        PayGatewayAccount gatewayAccount = payGatewayAccountQueryService.findById(payoutOrder.getPayGatewayAccountId());
        PayoutClient payoutClient = payoutClientMap.get(gatewayAccount.getPayGateway());
        Assert.notNull(payoutClient, thePaymentGatewayDoesNotExist());
        boolean checkSign = request.checkSign(gatewayAccount);
        if (!checkSign) {
            log.info("代付:通知:签名错误");
            throw new ExtendIllegalArgumentException("Signature error");
        }
        PayoutNotifyResponse response = payoutClient.payoutOrderNotify(gatewayAccount, request);
        if (PayoutOrderState.SUCCESS == response.getState()) {
            log.info("代付:成功:{},{}", gatewayAccount.getPayGateway(), request.getPayoutOrderId());
            stateHaeeendleService.success(payoutOrder);
        } else if (PayoutOrderState.FAIL == response.getState()) {
            log.info("代付:失败:{},{}", gatewayAccount.getPayGateway(), request.getPayoutOrderId());
            stateHaeeendleService.fail(payoutOrder);
        }
        payoutOrder.setNotifyResult(response.getNotifyResult());
        repository.save(payoutOrder);
        return response.getReturnText();
    }
}
