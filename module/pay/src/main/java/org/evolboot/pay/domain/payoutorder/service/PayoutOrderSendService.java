package org.evolboot.pay.domain.payoutorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.payout.PayoutClient;
import org.evolboot.pay.domain.paymentclient.payout.PayoutCreateRequest;
import org.evolboot.pay.domain.paymentclient.payout.PayoutCreateResponse;
import org.evolboot.pay.domain.paymentclient.payout.PayoutQueryResponse;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.PayoutOrderState;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class PayoutOrderSendService {

    private final PayoutOrderSupportService supportService;
    private final PayoutOrderRepository repository;
    private final Map<PayGateway, PayoutClient> payoutClientMap;
    private final PayGatewayAccountQueryService payGatewayAccountQueryService;
    private final PayoutOrderStateHandleService stateHandleService;

    protected PayoutOrderSendService(PayoutOrderRepository repository,
                                     PayoutOrderSupportService supportService,
                                     Map<PayGateway, PayoutClient> payoutClientMap,
                                     PayGatewayAccountQueryService payGatewayAccountQueryService,
                                     PayoutOrderStateHandleService stateHandleService) {
        this.repository = repository;
        this.supportService = supportService;
        this.payoutClientMap = payoutClientMap;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
        this.stateHandleService = stateHandleService;
    }

    public boolean send(String payoutOrderId) {
        PayoutOrder payoutOrder = supportService.findById(payoutOrderId);
        log.info("代付:发送请求到第三方:{},开始:{}", payoutOrder.getPayGateway(), payoutOrder.id());
        if (PayoutOrderState.WAIT.equals(payoutOrder.getState())) {
            log.info("代付:发送请求到第三方:{}:状态不对:{}", payoutOrder.getPayGateway(), payoutOrder.getState());
            return false;
        }
        PayoutClient payoutClient = payoutClientMap.get(payoutOrder.getPayGateway());
        PayGatewayAccount account = payGatewayAccountQueryService.findById(payoutOrder.getPayGatewayAccountId());

        PayoutQueryResponse payoutQueryResponse = payoutClient.queryPayoutdOrder(payoutOrderId, account);
        if (payoutQueryResponse.isExist()) {
            log.info("代付:发送请求到第三方:{},已存在订单:更新订单状态:{}", payoutOrder.getPayGateway(), JsonUtil.stringify(payoutQueryResponse));
            if (PayoutOrderState.SUCCESS.equals(payoutQueryResponse.getState())) {
                stateHandleService.success(payoutOrder);
            } else if (PayoutOrderState.FAIL.equals(payoutQueryResponse.getState())) {
                stateHandleService.fail(payoutOrder);
            } else {
                stateHandleService.pending(payoutOrder, payoutQueryResponse.getPoundageAmount(), payoutQueryResponse.getForeignOrderId());
            }
            payoutOrder.setQueryResult(payoutQueryResponse.getRequestResult());
            repository.save(payoutOrder);
            return true;
        }
        log.info("代付:发送请求到第三方:{},{}", payoutOrder.getPayGateway(), payoutOrder.id());
        PayoutCreateResponse response = payoutClient.createPayoutOrder(payoutOrderId, account, new PayoutCreateRequest(
                payoutOrder.getCurrency(),
                payoutOrder.getAmount(),
                payoutOrder.getPayeeName(),
                payoutOrder.getPayeePhone(),
                payoutOrder.getPayeeEmail(),
                payoutOrder.getBankCode(),
                payoutOrder.getBankNo(),
                payoutOrder.getIfscCode(),
                payoutOrder.getIfscCardNo(),
                payoutOrder.getOrgType()
        ));
        if (response.isOk()) {
            stateHandleService.pending(payoutOrder, response.getPoundageAmount(), response.getForeignOrderId());
            payoutOrder.setCreateResult(response.getCreateResult());
            repository.save(payoutOrder);
            return true;
        }
        log.info("代付:发送请求到第三方:{},出现错误,{},按失败的处理", payoutOrder.getPayGateway(), payoutOrder.id());
        stateHandleService.fail(payoutOrder);
        return false;
    }
}
