package org.evolboot.pay.domain.paygatewayaccount.repository;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQuery;

import java.util.List;
import java.util.Optional;

/**
 * 支付网关账户
 *
 * @author evol
 */
public interface PayGatewayAccountRepository {

    PayGatewayAccount save(PayGatewayAccount payGatewayAccount);

    Optional<PayGatewayAccount> findById(Long id);

    Page<PayGatewayAccount> page(PayGatewayAccountQuery query);

    void deleteById(Long id);

    List<PayGatewayAccount> findAll();

    List<PayGatewayAccount> findAll(PayGatewayAccountQuery query);

    Optional<PayGatewayAccount> findFirstByEnableIsTrue();
}
