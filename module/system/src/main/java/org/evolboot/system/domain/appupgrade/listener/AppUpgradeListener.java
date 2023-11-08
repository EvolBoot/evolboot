package org.evolboot.system.domain.appupgrade.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.appupgrade.repository.AppUpgradeRepository;
import org.evolboot.system.domain.appupgrade.service.AppUpgradeSupportService;
import org.springframework.stereotype.Service;

/**
 * APP更新
 *
 * @author evol
 */
@Service
@Slf4j
public class AppUpgradeListener {

    private final AppUpgradeRepository repository;

    private final AppUpgradeSupportService supportService;

    protected AppUpgradeListener(AppUpgradeRepository repository, AppUpgradeSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
