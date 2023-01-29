package org.evolboot.system.domain.appupgrade;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.appupgrade.repository.AppUpgradeRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * APP更新
 *
 * @author evol
 */
@Slf4j
public abstract class AppUpgradeSupportService {

    final AppUpgradeRepository repository;

    protected AppUpgradeSupportService(AppUpgradeRepository repository) {
        this.repository = repository;
    }

    public AppUpgrade findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(SystemI18nMessage.AppUpgrade.notFound()));
    }

}
