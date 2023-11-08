package org.evolboot.pay.domain.releasedorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.released.ReleasedClient;
import org.evolboot.pay.domain.paymentclient.released.ReleasedCreateRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedCreateResponse;
import org.evolboot.pay.domain.paymentclient.released.ReleasedQueryResponse;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReleasedOrderState;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author evol
 */
@Service
@Slf4j
public class ReleasedOrderSendService {

    private final ReleasedOrderSupportService supportService;

    private final ReleasedOrderRepository repository;

    private final Map<PayGateway, ReleasedClient> releasedClients;

    private final PayGatewayAccountQueryService payGatewayAccountQueryService;

    private final ReleasedOrderStateHandleService stateHandleService;


    protected ReleasedOrderSendService(ReleasedOrderRepository repository, ReleasedOrderSupportService supportService, Map<PayGateway, ReleasedClient> releasedClients, PayGatewayAccountQueryService payGatewayAccountQueryService, ReleasedOrderStateHandleService stateHandleService) {
        this.repository = repository;
        this.supportService = supportService;
        this.releasedClients = releasedClients;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
        this.stateHandleService = stateHandleService;
    }

    public boolean send(String releasedOrderId) {
        ReleasedOrder releasedOrder = supportService.findById(releasedOrderId);
        log.info("代付:发送请求到第三方:{},开始:{}", releasedOrder.getPayGateway(), releasedOrder.id());
        if (ReleasedOrderState.WAIT.equals(releasedOrder.getState())) {
            log.info("代付:发送请求到第三方:{}:状态不对:{}", releasedOrder.getPayGateway(), releasedOrder.getState());
            return false;
        }
        ReleasedClient releasedClient = releasedClients.get(releasedOrder.getPayGateway());
        PayGatewayAccount account = payGatewayAccountQueryService.findById(releasedOrder.getPayGatewayAccountId());

        ReleasedQueryResponse releasedQueryResponse = releasedClient.queryReleasedOrder(releasedOrderId, account);
        if (releasedQueryResponse.isExist()) {
            log.info("代付:发送请求到第三方:{},已存在订单:更新订单状态:{}", releasedOrder.getPayGateway(), JsonUtil.stringify(releasedQueryResponse));
            if (ReleasedOrderState.SUCCESS.equals(releasedQueryResponse.getState())) {
                stateHandleService.success(releasedOrder);
            } else if (ReleasedOrderState.FAIL.equals(releasedQueryResponse.getState())) {
                stateHandleService.fail(releasedOrder);
            } else {
                stateHandleService.pending(releasedOrder, releasedQueryResponse.getPoundageAmount(), releasedQueryResponse.getForeignOrderId());
            }
            releasedOrder.setQueryResult(releasedQueryResponse.getRequestResult());
            repository.save(releasedOrder);
            return true;
        }
        log.info("代付:发送请求到第三方:{},{}", releasedOrder.getPayGateway(), releasedOrder.id());
        ReleasedCreateResponse response = releasedClient.createReleasedOrder(releasedOrderId, account, new ReleasedCreateRequest(
                releasedOrder.getCurrency(),
                releasedOrder.getAmount(),
                releasedOrder.getPayeeName(),
                releasedOrder.getPayeePhone(),
                releasedOrder.getPayeeEmail(),
                releasedOrder.getBankCode(),
                releasedOrder.getBankNo(),
                releasedOrder.getIfscCode(),
                releasedOrder.getIfscCardNo(),
                releasedOrder.getOrgType()
        ));
        if (response.isOk()) {
            stateHandleService.pending(releasedOrder, response.getPoundageAmount(), response.getForeignOrderId());
            releasedOrder.setCreateResult(response.getCreateResult());
            repository.save(releasedOrder);
            return true;
        }
        log.info("代付:发送请求到第三方:{},出现错误,{},按失败的处理", releasedOrder.getPayGateway(), releasedOrder.id());
        stateHandleService.fail(releasedOrder);
        return false;
    }


}
