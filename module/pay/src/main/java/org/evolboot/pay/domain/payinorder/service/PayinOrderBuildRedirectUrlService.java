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

    private final Map<PayGateway, PayinClient> payinClientMap;

    private final PayGatewayAccountQueryService payGatewayAccountQueryService;

    protected PayinOrderBuildRedirectUrlService(PayinOrderRepository repository, PayinOrderSupportService supportService, RedisClientAppService redisClientAppService, Map<PayGateway, PayinClient> payinClientMap, PayGatewayAccountQueryService payGatewayAccountQueryService) {
        this.repository = repository;
        this.supportService = supportService;
        this.redisClientAppService = redisClientAppService;
        this.payinClientMap = payinClientMap;
        this.payGatewayAccountQueryService = payGatewayAccountQueryService;
    }


    public <T extends PayinRedirectNotifyRequest> String getPayinRedirectUrl(T request) {
        log.info("代收:前端回调:{}", JsonUtil.stringify(request));
        PayinOrder payinOrder = supportService.findById(request.getPayinOrderId());
        PayGatewayAccount payGatewayAccount = payGatewayAccountQueryService.findById(payinOrder.getPayGatewayAccountId());
        log.info("代收:前端回调:{}", payGatewayAccount.getPayGateway());
        PayinClient payinClient = payinClientMap.get(payGatewayAccount.getPayGateway());
        PayinRedirectNotifyResponse response = payinClient.payinOrderRedirectNotify(payGatewayAccount, request);

        String redirectUrl = payinOrder.getRedirectUrl();
        Map<String, String> params = Maps.newHashMap();
        params.put("internalOrderId", payinOrder.getInternalOrderId());
        params.put("state", response.getState().name());
        redisClientAppService.set(RedisCacheName.PAY_PAYIN_REDIRECT_URL_CACHE_KEY + payinOrder.getInternalOrderId(), params, 60);
        log.info("前端跳转链接:{},{},{}", payinOrder.id(), payinOrder.getPayGateway(), redirectUrl);

        return redirectUrl;
    }
}
