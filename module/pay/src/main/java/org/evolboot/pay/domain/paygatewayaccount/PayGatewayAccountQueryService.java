package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.dto.PayGatewayAccountQueryRequest;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 支付网关账户
 *
 * @author evol
 * @date 2023-06-14 20:25:41
 */
public interface PayGatewayAccountQueryService {

    PayGatewayAccount findById(Long id);

    List<PayGatewayAccount> findAll();

    List<PayGatewayAccount> findAll(PayGatewayAccountQueryRequest query);

    Page<PayGatewayAccount> page(PayGatewayAccountQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<PayGatewayAccount> findOne(PayGatewayAccountQueryRequest query);


}
