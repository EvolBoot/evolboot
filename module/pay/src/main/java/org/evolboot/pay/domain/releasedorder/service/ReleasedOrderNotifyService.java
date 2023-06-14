package org.evolboot.pay.domain.releasedorder.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ExtendIllegalArgumentException;
import org.evolboot.core.util.Assert;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.released.ReleasedClient;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyResponse;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReleasedOrderStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.evolboot.pay.PayI18nMessage.PaymentClient.thePaymentGatewayDoesNotExist;

/**
 * @author evol
 */
@Service
@Slf4j
public class ReleasedOrderNotifyService extends ReleasedOrderSupportService {


    private final Map<PayGateway, ReleasedClient> releasedClients;

    private final PayGatewayAccountQueryService payGatewayAccountQueryService;

    private final ReleasedOrderStatusHandleService statusHandleService;

    protected ReleasedOrderNotifyService(ReleasedOrderRepository repository, Map<PayGateway, ReleasedClient> releasedClients, PayGatewayAccountQueryService payGatewayAccountQueryService, ReleasedOrderStatusHandleService statusHandleService) {
        super(repository);
        this.releasedClients = releasedClients;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
        this.statusHandleService = statusHandleService;
    }


    public <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request) {
        log.info("代付:收到回调通知:{}", request.getNotifyParamsText());
        ReleasedOrder releasedOrder = findById(request.getReleasedOrderId());
        PayGatewayAccount gatewayAccount = payGatewayAccountQueryService.findById(releasedOrder.getPayGatewayAccountId());
        ReleasedClient releasedClient = releasedClients.get(gatewayAccount.getPayGateway());
        Assert.notNull(releasedClient, thePaymentGatewayDoesNotExist());
        boolean checkSign = request.checkSign(gatewayAccount);
        if (!checkSign) {
            log.info("代付:通知:签名错误");
            throw new ExtendIllegalArgumentException("Signature error");
        }
        ReleasedNotifyResponse response = releasedClient.releasedOrderNotify(gatewayAccount, request);
        if (ReleasedOrderStatus.SUCCESS == response.getStatus()) {
            log.info("代付:成功:{},{}", gatewayAccount.getPayGateway(), request.getReleasedOrderId());
            statusHandleService.success(releasedOrder);

        } else if (ReleasedOrderStatus.FAIL == response.getStatus()) {
            log.info("代付:失败:{},{}", gatewayAccount.getPayGateway(), request.getReleasedOrderId());
            statusHandleService.fail(releasedOrder);
        }
        releasedOrder.setNotifyResult(response.getNotifyResult());
        repository.save(releasedOrder);
        return response.getReturnText();
    }


}
