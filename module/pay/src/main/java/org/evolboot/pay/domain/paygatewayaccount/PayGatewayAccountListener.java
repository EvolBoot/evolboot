package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Service
@Slf4j
public class PayGatewayAccountListener extends PayGatewayAccountSupportService {

    protected PayGatewayAccountListener(PayGatewayAccountRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
