package org.evolboot.pay.domain.payoutorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.released.ReleasedClient;
import org.evolboot.pay.domain.paymentclient.released.ReleasedCreateRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedCreateResponse;
import org.evolboot.pay.domain.paymentclient.released.ReleasedQueryResponse;
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
    private final Map<PayGateway, ReleasedClient> releasedClients;
    private final PayGatewayAccountQueryService payGatewayAccountQueryService;
    private final PayoutOrderStateHandleService stateHandleService;

    protected PayoutOrderSendService(PayoutOrderRepository repository,
                                     PayoutOrderSupportService supportService,
                                     Map<PayGateway, ReleasedClient> releasedClients,
                                     PayGatewayAccountQueryService payGatewayAccountQueryService,
                                     PayoutOrderStateHandleService stateHandleService) {
        this.repository = repository;
        this.supportService = supportService;
        this.releasedClients = releasedClients;
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
        ReleasedClient releasedClient = releasedClients.get(payoutOrder.getPayGateway());
        PayGatewayAccount account = payGatewayAccountQueryService.findById(payoutOrder.getPayGatewayAccountId());

        ReleasedQueryResponse releasedQueryResponse = releasedClient.queryReleasedOrder(payoutOrderId, account);
        if (releasedQueryResponse.isExist()) {
            log.info("代付:发送请求到第三方:{},已存在订单:更新订单状态:{}", payoutOrder.getPayGateway(), JsonUtil.stringify(releasedQueryResponse));
            if (PayoutOrderState.SUCCESS.equals(releasedQueryResponse.getState())) {
                stateHandleService.success(payoutOrder);
            } else if (PayoutOrderState.FAIL.equals(releasedQueryResponse.getState())) {
                stateHandleService.fail(payoutOrder);
            } else {
                stateHandleService.pending(payoutOrder, releasedQueryResponse.getPoundageAmount(), releasedQueryResponse.getForeignOrderId());
            }
            payoutOrder.setQueryResult(releasedQueryResponse.getRequestResult());
            repository.save(payoutOrder);
            return true;
        }
        log.info("代付:发送请求到第三方:{},{}", payoutOrder.getPayGateway(), payoutOrder.id());
        ReleasedCreateResponse response = releasedClient.createReleasedOrder(payoutOrderId, account, new ReleasedCreateRequest(
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
