package org.evolboot.pay.domain.paygatewayaccount.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;

import java.util.List;
import java.util.Optional;

/**
 * 支付网关账户
 *
 * @author evol
 */
public interface PayGatewayAccountRepository extends BaseRepository<PayGatewayAccount, Long> {

    PayGatewayAccount save(PayGatewayAccount payGatewayAccount);

    Optional<PayGatewayAccount> findById(Long id);

    Optional<PayGatewayAccount> findByAlias(String alias);


    void deleteById(Long id);

    List<PayGatewayAccount> findAll();


    Optional<PayGatewayAccount> findFirstByEnableIsTrue();


    <Q extends Query> List<PayGatewayAccount> findAll(Q query);

    <Q extends Query> Optional<PayGatewayAccount> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<PayGatewayAccount> page(Q query);


}
