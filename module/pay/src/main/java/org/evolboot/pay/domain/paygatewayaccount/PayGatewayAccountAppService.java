package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountCreateFactory;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountQuery;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 支付网关账户
 *
 * @author evol
 */
public interface PayGatewayAccountAppService {

    PayGatewayAccount create(PayGatewayAccountCreateFactory.Request request);

    void update(PayGatewayAccountUpdateService.Request request);

    void delete(Long id);


}
