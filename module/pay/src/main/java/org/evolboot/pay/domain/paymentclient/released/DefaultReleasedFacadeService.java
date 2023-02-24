package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.core.util.Assert;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReleasedOrderStatus;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountAppService;
import org.evolboot.pay.domain.releasedorder.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderAppService;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderCreateFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.evolboot.pay.PayI18nMessage.PaymentClient.thePaymentGatewayDoesNotExist;

/**
 * 代付,门面服务
 *
 * @author evol
 */
@Slf4j
@Service
public class DefaultReleasedFacadeService implements ReleasedFacadeService {


    private final Map<PayGateway, ReleasedClient> releasedClients;

    private final PayGatewayAccountAppService payGatewayAccountAppService;

    private final ReleasedOrderAppService releasedOrderAppService;

    public DefaultReleasedFacadeService(Map<PayGateway, ReleasedClient> releasedClients, PayGatewayAccountAppService payGatewayAccountAppService, ReleasedOrderAppService releasedOrderAppService) {
        this.releasedClients = releasedClients;
        this.payGatewayAccountAppService = payGatewayAccountAppService;
        this.releasedOrderAppService = releasedOrderAppService;
    }


    @Override
    @Transactional
    public ReleasedOrderResponse createReleasedOrder(ReleasedOrderRequest request) {
        log.info("代付:创建代付订单:{}", JsonUtil.stringify(request));
        PayGatewayAccount gatewayAccount = payGatewayAccountAppService.findById(request.getPayGatewayAccountId());
        ReleasedClient releasedClient = releasedClients.get(gatewayAccount.getPayGateway());
        Assert.notNull(releasedClient, thePaymentGatewayDoesNotExist());
        String releasedOrderId = ReleasedOrder.generateId();
        log.info("代付:进入,网关:{},内部订单号:{},代付订单号:{}", gatewayAccount.getPayGateway(), request.getInternalOrderId(), releasedOrderId);
        ReleasedOrderResponse response = releasedClient.createReleasedOrder(releasedOrderId, gatewayAccount, request);
        ReleasedOrderStatus status = ReleasedOrderStatus.PENDING;
        if (!response.isOk()) {
            status = ReleasedOrderStatus.FAIL;
        }
        releasedOrderAppService.create(new ReleasedOrderCreateFactory.Request(
                releasedOrderId,
                request.getType(),
                request.getInternalOrderId(),
                request.getAmount(),
                request.getBankBankCardholderName(),
                request.getCustomMobile(),
                request.getCustomEmail(),
                request.getBankBankCardholderName(),
                request.getBankCode(),
                request.getBankNo(),
                gatewayAccount.id(),
                releasedClient.getPayGateway(),
                response.getPoundage(),
                response.getForeignOrderId(),
                response.getRequestResult(),
                status
        ));
        return response;
    }

    @Override
    @Transactional
    public <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request) {
        log.info("代付:收到回调通知:{},{}", request.getPayGateway(), request.getNotifyParamsText());
        ReleasedClient releasedClient = releasedClients.get(request.getPayGateway());
        ReleasedOrder releasedOrder = releasedOrderAppService.findById(request.getReleasedOrderId());
        PayGatewayAccount gatewayAccount = payGatewayAccountAppService.findById(releasedOrder.getPayGatewayAccountId());
        request.checkSign(gatewayAccount.getSecretKey());
        ReleasedNotifyResponse response = releasedClient.releasedOrderNotify(gatewayAccount, request);
        if (response.isOk()) {
            log.info("代付:成功:{},{}", request.getPayGateway(), request.getReleasedOrderId());
            releasedOrderAppService.success(releasedOrder.id(), response.getNotifyResult());
        } else {
            log.info("代付:失败:{},{}", request.getPayGateway(), request.getReleasedOrderId());
            releasedOrderAppService.fail(releasedOrder.id(), response.getNotifyResult());
        }
        return response.getReturnText();
    }

}
