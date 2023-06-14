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
import org.evolboot.shared.pay.ReleasedOrderStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author evol
 */
@Service
@Slf4j
public class ReleasedOrderSendService extends ReleasedOrderSupportService {


    private final Map<PayGateway, ReleasedClient> releasedClients;

    private final PayGatewayAccountQueryService payGatewayAccountQueryService;

    private final ReleasedOrderStatusHandleService statusHandleService;


    protected ReleasedOrderSendService(ReleasedOrderRepository repository, Map<PayGateway, ReleasedClient> releasedClients, PayGatewayAccountQueryService payGatewayAccountQueryService, ReleasedOrderStatusHandleService statusHandleService) {
        super(repository);
        this.releasedClients = releasedClients;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
        this.statusHandleService = statusHandleService;
    }

    public boolean send(String releasedOrderId) {
        ReleasedOrder releasedOrder = findById(releasedOrderId);
        log.info("代付:发送请求到第三方:{},开始:{}", releasedOrder.getPayGateway(), releasedOrder.id());
        if (ReleasedOrderStatus.WAIT.equals(releasedOrder.getStatus())) {
            log.info("代付:发送请求到第三方:{}:状态不对:{}", releasedOrder.getPayGateway(), releasedOrder.getStatus());
            return false;
        }
        ReleasedClient releasedClient = releasedClients.get(releasedOrder.getPayGateway());
        PayGatewayAccount account = payGatewayAccountQueryService.findById(releasedOrder.getPayGatewayAccountId());

        ReleasedQueryResponse releasedQueryResponse = releasedClient.queryReleasedOrder(releasedOrderId, account);
        if (releasedQueryResponse.isExist()) {
            log.info("代付:发送请求到第三方:{},已存在订单:更新订单状态:{}", releasedOrder.getPayGateway(), JsonUtil.stringify(releasedQueryResponse));
            if (ReleasedOrderStatus.SUCCESS.equals(releasedQueryResponse.getStatus())) {
                statusHandleService.success(releasedOrder);
            } else if (ReleasedOrderStatus.FAIL.equals(releasedQueryResponse.getStatus())) {
                statusHandleService.fail(releasedOrder);
            } else {
                statusHandleService.pending(releasedOrder, releasedQueryResponse.getPoundageAmount(), releasedQueryResponse.getForeignOrderId());
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
            statusHandleService.pending(releasedOrder, response.getPoundageAmount(), response.getForeignOrderId());
            releasedOrder.setCreateResult(response.getCreateResult());
            repository.save(releasedOrder);
            return true;
        }
        log.info("代付:发送请求到第三方:{},出现错误,{},按失败的处理", releasedOrder.getPayGateway(), releasedOrder.id());
        statusHandleService.fail(releasedOrder);
        return false;
    }


}
