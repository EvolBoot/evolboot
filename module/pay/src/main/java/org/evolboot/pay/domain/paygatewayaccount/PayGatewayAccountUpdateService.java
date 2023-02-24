package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Slf4j
@Service
public class PayGatewayAccountUpdateService extends PayGatewayAccountSupportService {
    protected PayGatewayAccountUpdateService(PayGatewayAccountRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        PayGatewayAccount payGatewayAccount = findById(id);
        payGatewayAccount.setLogo(request.getLogo());
        payGatewayAccount.setMerchantId(request.getMerchantId());
        payGatewayAccount.setAppid(request.getAppid());
        payGatewayAccount.setSecretKey(request.getSecretKey());
        payGatewayAccount.setMinimumReceipt(request.getMinimumReceipt());
        payGatewayAccount.setMaximumReceipt(request.getMaximumReceipt());
        payGatewayAccount.setEnable(request.getEnable());
        payGatewayAccount.setPayGateway(request.getPayGateway());
        payGatewayAccount.setLocales(request.getLocales());
        payGatewayAccount.setSort(request.getSort());
        payGatewayAccount.setWalletId(request.getWalletId());
        repository.save(payGatewayAccount);
    }

    public static class Request extends PayGatewayAccountRequestBase {
    }

}
