package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.core.exception.ExtendIllegalArgumentException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.exception.PayException;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReleasedOrderStatus;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountAppService;
import org.evolboot.pay.domain.releasedorder.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderAppService;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderCreateFactory;
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
    public ReleasedCreateResponse createReleasedOrder(ReleasedCreateRequest request) {
        log.info("代付:创建订单:{}", JsonUtil.stringify(request));
        PayGatewayAccount gatewayAccount = payGatewayAccountAppService.findById(request.getPayGatewayAccountId());
        ReleasedClient releasedClient = releasedClients.get(gatewayAccount.getPayGateway());
        // 支持该下发组织
        if (!releasedClient.supportOrgType(request.getOrgType())) {
            log.info("代付:创建订单:不支持该组织:网关:{},组织:{}", gatewayAccount.getPayGateway(), request.getOrgType());
            throw PayException.dotSupportOrgType(gatewayAccount.getAlias(), request.getOrgType());
        }
        if (!releasedClient.supportCurrency(request.getCurrency())) {
            log.info("代付:创建订单:不支持该货币:网关:{},货币:{}", gatewayAccount.getPayGateway(), request.getCurrency());
            throw PayException.dotSupportCurrency(gatewayAccount.getAlias(), request.getCurrency());
        }
        Assert.notNull(releasedClient, thePaymentGatewayDoesNotExist());
        String releasedOrderId = ReleasedOrder.generateId();
        log.info("代付:进入,网关:{},内部订单号:{},代付订单号:{}", gatewayAccount.getPayGateway(), request.getInternalOrderId(), releasedOrderId);
        ReleasedCreateResponse response = releasedClient.createReleasedOrder(releasedOrderId, gatewayAccount, request);
        ReleasedOrderStatus status = ReleasedOrderStatus.PENDING;
        Assert.isTrueOrElseThrow(response.isOk(), () -> PayException.RELEASED_ORDER_ERROR);
        releasedOrderAppService.create(new ReleasedOrderCreateFactory.Request(
                releasedOrderId,
                request.getInternalOrderId(),
                request.getCurrency(),
                request.getAmount(),
                request.getPayeeName(),
                request.getPayeePhone(),
                request.getPayeeEmail(),
                request.getBankCode(),
                request.getBankNo(),
                request.getIfscCode(),
                request.getIfscCardNo(),
                request.getOrgType(),
                gatewayAccount.id(),
                releasedClient.getPayGateway(),
                response.getPoundageAmount(),
                response.getForeignOrderId(),
                response.getRequestResult(),
                status
        ));
        return response;
    }

    @Override
    @Transactional
    public <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request) {
        log.info("代付:收到回调通知:{}", request.getNotifyParamsText());
        ReleasedOrder releasedOrder = releasedOrderAppService.findById(request.getReleasedOrderId());
        PayGatewayAccount gatewayAccount = payGatewayAccountAppService.findById(releasedOrder.getPayGatewayAccountId());
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
            releasedOrderAppService.success(releasedOrder.id(), response.getNotifyResult());
        } else if (ReleasedOrderStatus.FAIL == response.getStatus()) {
            log.info("代付:失败:{},{}", gatewayAccount.getPayGateway(), request.getReleasedOrderId());
            releasedOrderAppService.fail(releasedOrder.id(), response.getNotifyResult());
        }
        return response.getReturnText();
    }

}
