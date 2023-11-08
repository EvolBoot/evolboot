package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.pay.PayAccessAuthorities;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountCreateFactory;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountSupportService;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountUpdateService;

import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 支付网关账户
 *
 * @author evol
 * @date 2023-06-14 20:25:41
 */
@Slf4j
@Service
public class PayGatewayAccountQueryServiceImpl  implements PayGatewayAccountQueryService {

    private final PayGatewayAccountRepository repository;
    private final PayGatewayAccountSupportService supportService;

    protected PayGatewayAccountQueryServiceImpl(PayGatewayAccountRepository repository, PayGatewayAccountSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public PayGatewayAccount findById(Long id) {
        return supportService.findById(id);
    }


    @Override
    public List<PayGatewayAccount> findAll() {
        return repository.findAll();
    }


    @Override
    public List<PayGatewayAccount> findAll(PayGatewayAccountQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<PayGatewayAccount> page(PayGatewayAccountQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<PayGatewayAccount> findOne(PayGatewayAccountQuery query) {
        return repository.findOne(query);
    }
}
