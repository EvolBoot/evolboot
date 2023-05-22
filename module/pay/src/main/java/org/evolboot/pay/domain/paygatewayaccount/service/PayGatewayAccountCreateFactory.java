package org.evolboot.pay.domain.paygatewayaccount.service;

import lombok.extern.slf4j.Slf4j;
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
public class PayGatewayAccountCreateFactory extends PayGatewayAccountSupportService {
    protected PayGatewayAccountCreateFactory(PayGatewayAccountRepository repository) {
        super(repository);
    }

    public PayGatewayAccount execute(Request request) {
        PayGatewayAccount payGatewayAccount = new PayGatewayAccount(
                request.getLogo(),
                request.getMerchantId(),
                request.getAppid(),
                request.getSecretKey(),
                request.getMinimumReceipt(),
                request.getMaximumReceipt(),
                request.getEnable(),
                request.getPayGateway(),
                request.getSort(),
                request.getWalletId(),
                request.getLocales(),
                request.getAlias()
        );
        repository.save(payGatewayAccount);
        return payGatewayAccount;
    }

    public static class Request extends PayGatewayAccountRequestBase {

    }

}