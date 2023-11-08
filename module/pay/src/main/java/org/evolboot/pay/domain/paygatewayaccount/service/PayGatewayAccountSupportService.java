package org.evolboot.pay.domain.paygatewayaccount.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import org.springframework.stereotype.Service;

import static org.evolboot.pay.exception.PayException.GATEWAY_NOT_FOUND;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Slf4j
@Service
public class PayGatewayAccountSupportService {

    protected final PayGatewayAccountRepository repository;

    protected PayGatewayAccountSupportService(PayGatewayAccountRepository repository) {
        this.repository = repository;
    }

    public PayGatewayAccount findById(Long id) {
        return repository.findById(id).orElseThrow(() -> GATEWAY_NOT_FOUND);
    }

}
