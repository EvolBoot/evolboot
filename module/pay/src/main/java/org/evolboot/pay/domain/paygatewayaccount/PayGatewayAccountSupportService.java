package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Slf4j
public abstract class PayGatewayAccountSupportService {

    final PayGatewayAccountRepository repository;

    protected PayGatewayAccountSupportService(PayGatewayAccountRepository repository) {
        this.repository = repository;
    }

    public PayGatewayAccount findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(PayI18nMessage.PayGatewayAccount.notFound()));
    }

}
