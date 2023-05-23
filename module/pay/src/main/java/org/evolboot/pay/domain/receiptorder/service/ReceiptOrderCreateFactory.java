package org.evolboot.pay.domain.receiptorder.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountAppService;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptClient;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptCreateRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptCreateResponse;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.pay.exception.PayException;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static org.evolboot.pay.PayI18nMessage.PaymentClient.thePaymentGatewayDoesNotExist;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@Service
public class ReceiptOrderCreateFactory extends ReceiptOrderSupportService {


    private final Map<PayGateway, ReceiptClient> receiptClients;

    private final PayGatewayAccountAppService payGatewayAccountAppService;


    protected ReceiptOrderCreateFactory(ReceiptOrderRepository repository, Map<PayGateway, ReceiptClient> receiptClients, PayGatewayAccountAppService payGatewayAccountAppService) {
        super(repository);
        this.receiptClients = receiptClients;
        this.payGatewayAccountAppService = payGatewayAccountAppService;
    }

    public ReceiptOrder execute(Request request) {

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
        ReceiptCreateResponse response = receiptClient.createReceiptOrder(receiptOrderId, gatewayAccount, request.to());
        Assert.isTrueOrElseThrow(response.isOk(), () -> PayException.RECEIPT_ORDER_ERROR);

        ReceiptOrder receiptOrder = new ReceiptOrder(
                receiptOrderId,
                request.getInternalOrderId(),
                request.getProductName(),
                request.getPayeeName(),
                request.getPayeePhone(),
                request.getPayeeEmail(),
                request.getPayGatewayAccountId(),
                request.getPayAmount(),
                gatewayAccount.getPayGateway(),
                request.getRedirectUrl(),
                request.getCurrency(),
                response.getRequestResult()
        );
        repository.save(receiptOrder);
        return receiptOrder;
    }

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Request {


        // 内部订单ID
        private String internalOrderId;

        // 商品信息
        private String productName;

        // 支付人姓名
        private String payeeName;

        private String payeePhone;

        private String payeeEmail;

        // 支付金额
        private BigDecimal payAmount;

        private Currency currency;

        private Long payGatewayAccountId;

        private String redirectUrl;

        private String upi;

        private String methodId;

        public ReceiptCreateRequest to() {
            return new ReceiptCreateRequest(internalOrderId, productName, payeeName, payeePhone, payeeEmail, payAmount, currency, payGatewayAccountId, redirectUrl, upi, methodId);
        }

    }


}
