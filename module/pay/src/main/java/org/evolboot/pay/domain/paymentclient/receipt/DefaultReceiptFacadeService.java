package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.core.util.Assert;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.receiptorder.ReceiptOrder;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountAppService;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderAppService;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderCreateFactory;
import org.evolboot.pay.exception.PayException;
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
    public ReceiptOrderResponse createReceiptOrder(ReceiptOrderRequest request) {
        log.info("代收:进入代收:{}", JsonUtil.stringify(request));
        PayGatewayAccount gatewayAccount = payGatewayAccountAppService.findById(request.getPayGatewayAccountId());
        log.info("代收:网关:{}", gatewayAccount.getPayGateway());
        ReceiptClient receiptClient = receiptClients.get(gatewayAccount.getPayGateway());
        Assert.notNull(receiptClient, thePaymentGatewayDoesNotExist());
        String receiptOrderId = ReceiptOrder.generateId();
        log.info("代收:进入代收支付,网关:{},内部单号:{},代收订单:{}", gatewayAccount.getPayGateway(), request.getInternalOrderId(), receiptOrderId);
        ReceiptOrderResponse response = receiptClient.createReceiptOrder(receiptOrderId, gatewayAccount, request);
        if (response.isOk()) {
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
                            request.getCallbackUrl(),
                            response.getRequestResult()
                    )
            );
            return response;
        }
        throw new PayException("Pay Fail");
    }

    @Override
    @Transactional
    public <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T parameters) {
        log.info("代收:回调网关:{},数据:{}", parameters.getPayGateway(), parameters);
        ReceiptClient receiptClient = receiptClients.get(parameters.getPayGateway());
        Assert.notNull(receiptClient, thePaymentGatewayDoesNotExist());
        String receiptOrderId = parameters.getReceiptOrderId();
        ReceiptOrder receiptOrder = receiptOrderAppService.findById(receiptOrderId);
        PayGatewayAccount payGatewayAccount = payGatewayAccountAppService.findById(receiptOrder.getPayGatewayAccountId());
        parameters.checkSign(payGatewayAccount.getSecretKey());
        ReceiptNotifyResponse receiptNotifyResponse = receiptClient.receiptOrderNotify(payGatewayAccount, parameters);
        if (receiptNotifyResponse.isOk()) {
            log.info("代收:支付成功:{},{}", parameters.getPayGateway(), receiptOrder.id());
            receiptOrderAppService.success(receiptOrderId, receiptNotifyResponse.getNotifyResult());
        } else {
            log.info("代收:支付失败:{},{}", parameters.getPayGateway(), receiptOrder.id());
            receiptOrderAppService.fail(receiptOrderId, receiptNotifyResponse.getNotifyResult());
        }
        return receiptNotifyResponse.getReturnText();
    }
}
