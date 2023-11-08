package org.evolboot.pay.domain.paygatewayaccount.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountSupportService;
import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import org.springframework.stereotype.Service;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Service
@Slf4j
public class PayGatewayAccountListener {

    private final PayGatewayAccountRepository repository;

    private final PayGatewayAccountSupportService supportService;

    protected PayGatewayAccountListener(PayGatewayAccountRepository repository, PayGatewayAccountSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
    /*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
