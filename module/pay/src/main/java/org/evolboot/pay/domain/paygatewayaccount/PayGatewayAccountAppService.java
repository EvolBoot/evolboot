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

    List<PayGatewayAccount> findAll();

    Optional<PayGatewayAccount> findFirstByEnableIsTrue();

    List<PayGatewayAccount> findAll(PayGatewayAccountQuery query);

    Page<PayGatewayAccount> page(PayGatewayAccountQuery query);

    PayGatewayAccount findById(Long id);

    PayGatewayAccount findByAlias(String alias);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<PayGatewayAccount> findOne(PayGatewayAccountQuery query);


}
