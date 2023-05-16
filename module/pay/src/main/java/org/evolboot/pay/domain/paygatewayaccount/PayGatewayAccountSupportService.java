package org.evolboot.pay.domain.paygatewayaccount;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;

import static org.evolboot.pay.exception.PayException.GATEWAY_NOT_FOUND;

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
        return repository.findById(id).orElseThrow(() -> GATEWAY_NOT_FOUND);
    }

}
