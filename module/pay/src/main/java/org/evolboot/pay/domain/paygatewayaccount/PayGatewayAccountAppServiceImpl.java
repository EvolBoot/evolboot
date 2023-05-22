package org.evolboot.pay.domain.paygatewayaccount;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountCreateFactory;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountQuery;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountSupportService;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.evolboot.pay.exception.PayException.GATEWAY_NOT_FOUND;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Slf4j
@Service
public class PayGatewayAccountAppServiceImpl extends PayGatewayAccountSupportService implements PayGatewayAccountAppService {


    private final PayGatewayAccountCreateFactory factory;
    private final PayGatewayAccountUpdateService updateService;

    protected PayGatewayAccountAppServiceImpl(PayGatewayAccountRepository repository, PayGatewayAccountCreateFactory factory, PayGatewayAccountUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public PayGatewayAccount create(PayGatewayAccountCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, PayGatewayAccountUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<PayGatewayAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<PayGatewayAccount> findFirstByEnableIsTrue() {
        return repository.findFirstByEnableIsTrue();
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
    public PayGatewayAccount findByAlias(String alias) {
        return repository.findByAlias(alias).orElseThrow(() -> GATEWAY_NOT_FOUND);
    }


    @Override
    public Optional<PayGatewayAccount> findOne(PayGatewayAccountQuery query) {
        return repository.findOne(query);
    }

}
