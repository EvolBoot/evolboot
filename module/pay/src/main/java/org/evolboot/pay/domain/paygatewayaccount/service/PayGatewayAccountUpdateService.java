package org.evolboot.pay.domain.paygatewayaccount.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.paygatewayaccount.dto.PayGatewayAccountRequestBase;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import org.springframework.stereotype.Service;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Slf4j
@Service
public class PayGatewayAccountUpdateService {

    private final PayGatewayAccountRepository repository;
    private final PayGatewayAccountSupportService supportService;

    protected PayGatewayAccountUpdateService(PayGatewayAccountRepository repository, PayGatewayAccountSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        PayGatewayAccount payGatewayAccount = supportService.findById(request.getId());
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
        payGatewayAccount.setAlias(request.getAlias());
        repository.save(payGatewayAccount);
    }

    @Getter
    @Setter
    public static class Request extends PayGatewayAccountRequestBase {
        private Long id;
    }

}
