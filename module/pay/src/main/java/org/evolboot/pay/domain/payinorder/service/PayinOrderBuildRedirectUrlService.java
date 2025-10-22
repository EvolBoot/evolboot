package org.evolboot.pay.domain.payinorder.service;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.service.RedisClientAppService;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.payin.PayinClient;
import org.evolboot.pay.domain.paymentclient.payin.PayinRedirectNotifyRequest;
import org.evolboot.pay.domain.paymentclient.payin.PayinRedirectNotifyResponse;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.repository.PayinOrderRepository;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.shared.pay.PayGateway;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author evol
 */
@Service
@Slf4j
public class PayinOrderBuildRedirectUrlService {

    private final PayinOrderSupportService supportService;

    private final PayinOrderRepository repository;

    private final RedisClientAppService redisClientAppService;

    private final Map<PayGateway, PayinClient> receiptClients;

    private final PayGatewayAccountQueryService payGatewayAccountQueryService;

    protected PayinOrderBuildRedirectUrlService(PayinOrderRepository repository, PayinOrderSupportService supportService, RedisClientAppService redisClientAppService, Map<PayGateway, PayinClient> receiptClients, PayGatewayAccountQueryService payGatewayAccountQueryService) {
        this.repository = repository;
        this.supportService = supportService;
        this.redisClientAppService = redisClientAppService;
        this.receiptClients = receiptClients;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
    }


    public <T extends PayinRedirectNotifyRequest> String getReceiptRedirectUrl(T request) {
        log.info("代收:前端回调:{}", JsonUtil.stringify(request));
        PayinOrder receiptOrder = supportService.findById(request.getReceiptOrderId());
        PayGatewayAccount payGatewayAccount = payGatewayAccountQueryService.findById(receiptOrder.getPayGatewayAccountId());
        log.info("代收:前端回调:{}", payGatewayAccount.getPayGateway());
        PayinClient receiptClient = receiptClients.get(payGatewayAccount.getPayGateway());
        PayinRedirectNotifyResponse response = receiptClient.payinOrderRedirectNotify(payGatewayAccount, request);

        String redirectUrl = receiptOrder.getRedirectUrl();
        Map<String, String> params = Maps.newHashMap();
        params.put("internalOrderId", receiptOrder.getInternalOrderId());
        params.put("state", response.getState().name());
        redisClientAppService.set(RedisCacheName.PAY_RECEIPT_REDIRECT_URL_CACHE_KEY + receiptOrder.getInternalOrderId(), params, 60);
        log.info("前端跳转链接:{},{},{}", receiptOrder.id(), receiptOrder.getPayGateway(), redirectUrl);

        return redirectUrl;
    }
}
