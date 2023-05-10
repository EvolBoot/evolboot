package org.evolboot.system.domain.appupgrade;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.appupgrade.repository.AppUpgradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.appupgrade.service.AppUpgradeCreateFactory;
import org.evolboot.system.domain.appupgrade.service.AppUpgradeSupportService;
import org.evolboot.system.domain.appupgrade.service.AppUpgradeUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * APP更新
 *
 * @author evol
 */
@Slf4j
@Service
public class DefaultAppUpgradeAppService extends AppUpgradeSupportService implements AppUpgradeAppService {


    private final AppUpgradeCreateFactory factory;
    private final AppUpgradeUpdateService updateService;

    protected DefaultAppUpgradeAppService(AppUpgradeRepository repository, AppUpgradeCreateFactory factory, AppUpgradeUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public AppUpgrade create(AppUpgradeCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, AppUpgradeUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<AppUpgrade> findAll() {
        return repository.findAll();
    }


    @Override
    public List<AppUpgrade> findAll(AppUpgradeQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<AppUpgrade> page(AppUpgradeQuery query) {
        return repository.page(query);
    }

    @Override
    public AppUpgrade check(ClientType clientType) {
        return repository.findFirstByClientTypeOrderByAppVersionDesc(clientType);
    }


    @Override
    public Optional<AppUpgrade> findOne(AppUpgradeQuery query) {
        return repository.findOne(query);
    }

}
