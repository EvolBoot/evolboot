package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.core.exception.ExtendIllegalArgumentException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.receiptorder.ReceiptOrder;
import org.evolboot.pay.exception.PayException;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountAppService;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderAppService;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderCreateFactory;
import org.evolboot.shared.pay.ReceiptOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.evolboot.pay.PayI18nMessage.PaymentClient.thePaymentGatewayDoesNotExist;

/**
 * 代收,门面服务
 *
 * @author evol
 */
@Service
@Slf4j
public class DefaultReceiptFacadeService implements ReceiptFacadeService {

    private final Map<PayGateway, ReceiptClient> receiptClients;

    private final PayGatewayAccountAppService payGatewayAccountAppService;

    private final ReceiptOrderAppService receiptOrderAppService;


    public DefaultReceiptFacadeService(Map<PayGateway, ReceiptClient> receiptClients, PayGatewayAccountAppService payGatewayAccountAppService, ReceiptOrderAppService receiptOrderAppService) {
        this.receiptClients = receiptClients;
        this.payGatewayAccountAppService = payGatewayAccountAppService;
        this.receiptOrderAppService = receiptOrderAppService;
    }

    @Override
    @Transactional
    public ReceiptCreateResponse createReceiptOrder(ReceiptCreateRequest request) {
        log.info("代收:创建订单:{}", JsonUtil.stringify(request));
        PayGatewayAccount gatewayAccount = payGatewayAccountAppService.findById(request.getPayGatewayAccountId());
        log.info("代收:网关:{}", gatewayAccount.getPayGateway());
        ReceiptClient receiptClient = receiptClients.get(gatewayAccount.getPayGateway());

        if (!receiptClient.supportCurrency(request.getCurrency())) {
            log.info("代收:创建订单:不支持该货币:网关:{},货币:{}", gatewayAccount.getPayGateway(), request.getCurrency());
            throw PayException.dotSupportCurrency(gatewayAccount.getAlias(), request.getCurrency());
        }
        Assert.notNull(receiptClient, thePaymentGatewayDoesNotExist());
        String receiptOrderId = ReceiptOrder.generateId();
        log.info("代收:创建代收,网关:{},内部单号:{},代收订单:{}", gatewayAccount.getPayGateway(), request.getInternalOrderId(), receiptOrderId);
        ReceiptCreateResponse response = receiptClient.createReceiptOrder(receiptOrderId, gatewayAccount, request);
        Assert.isTrueOrElseThrow(response.isOk(), () -> PayException.RECEIPT_ORDER_ERROR);
        receiptOrderAppService.create(
                new ReceiptOrderCreateFactory.Request(
                        receiptOrderId,
                        request.getInternalOrderId(),
                        request.getProductName(),
                        request.getPayeeName(),
                        request.getPayeePhone(),
                        request.getPayeeEmail(),
                        gatewayAccount.id(),
                        request.getPayAmount(),
                        gatewayAccount.getPayGateway(),
                        request.getRedirectUrl(),
                        response.getRequestResult()
                )
        );
        return response;
    }

    @Override
    @Transactional
    public <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T request) {
        log.info("代收:收到通知:数据:{}", JsonUtil.stringify(request));
        String receiptOrderId = request.getReceiptOrderId();
        ReceiptOrder receiptOrder = receiptOrderAppService.findById(receiptOrderId);
        PayGatewayAccount payGatewayAccount = payGatewayAccountAppService.findById(receiptOrder.getPayGatewayAccountId());
        ReceiptClient receiptClient = receiptClients.get(payGatewayAccount.getPayGateway());
        Assert.notNull(receiptClient, thePaymentGatewayDoesNotExist());
        boolean checkSign = request.checkSign(payGatewayAccount.getSecretKey());
        if (!checkSign) {
            log.info("代收:通知:签名错误");
            throw new ExtendIllegalArgumentException("Signature error");
        }
        ReceiptNotifyResponse response = receiptClient.receiptOrderNotify(payGatewayAccount, request);
        if (ReceiptOrderStatus.SUCCESS == response.getStatus()) {
            log.info("代收:支付成功:{},{}", payGatewayAccount.getPayGateway(), receiptOrder.id());
            receiptOrderAppService.success(receiptOrderId, response.getNotifyResult());
        } else if (ReceiptOrderStatus.FAIL == response.getStatus()) {
            log.info("代收:支付失败:{},{}", payGatewayAccount.getPayGateway(), receiptOrder.id());
            receiptOrderAppService.fail(receiptOrderId, response.getNotifyResult());
        }
        return response.getReturnText();
    }


    @Override
    public <T extends ReceiptRedirectNotifyRequest> String getReceiptRedirectUrl(T request) {
        log.info("代收:前端回调:{}", JsonUtil.stringify(request));
        ReceiptOrder receiptOrder = receiptOrderAppService.findById(request.getReceiptOrderId());
        PayGatewayAccount payGatewayAccount = payGatewayAccountAppService.findById(receiptOrder.getPayGatewayAccountId());
        ReceiptClient receiptClient = receiptClients.get(payGatewayAccount.getPayGateway());
        ReceiptRedirectNotifyResponse response = receiptClient.receiptOrderRedirectNotify(payGatewayAccount, request);
        return receiptOrderAppService.getRedirectUrl(request.getReceiptOrderId(), response.getStatus());
    }

}
