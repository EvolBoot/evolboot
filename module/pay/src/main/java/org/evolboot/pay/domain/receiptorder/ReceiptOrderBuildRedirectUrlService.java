package org.evolboot.pay.domain.receiptorder;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.service.RedisClientAppService;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderSupportService;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.shared.pay.ReceiptOrderStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhangsan
 */
@Service
@Slf4j
public class ReceiptOrderBuildRedirectUrlService extends ReceiptOrderSupportService {

    private final RedisClientAppService redisClientAppService;

    protected ReceiptOrderBuildRedirectUrlService(ReceiptOrderRepository repository, RedisClientAppService redisClientAppService) {
        super(repository);
        this.redisClientAppService = redisClientAppService;
    }

    public String execute(String id, ReceiptOrderStatus status) {
        ReceiptOrder receiptOrder = findById(id);
        String redirectUrl = receiptOrder.getRedirectUrl();
        Map<String, String> params = Maps.newHashMap();
        params.put("internalOrderId", receiptOrder.getInternalOrderId());
        params.put("status", status.name());
        redisClientAppService.set(RedisCacheName.PAY_RECEIPT_REDIRECT_URL_CACHE_KEY + receiptOrder.getInternalOrderId(), params, 60);
        log.info("前端跳转链接:{},{},{}", id, receiptOrder.getPayGateway(), redirectUrl);
        return redirectUrl;
    }
}
