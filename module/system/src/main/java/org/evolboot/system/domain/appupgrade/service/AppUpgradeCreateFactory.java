package org.evolboot.system.domain.appupgrade.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.appupgrade.entity.AppUpgrade;
import org.evolboot.system.domain.appupgrade.repository.AppUpgradeRepository;
import org.springframework.stereotype.Service;

/**
 * APP更新
 *
 * @author evol
 */
@Slf4j
@Service
public class AppUpgradeCreateFactory {

    private final AppUpgradeRepository repository;
    private final AppUpgradeSupportService supportService;

    protected AppUpgradeCreateFactory(AppUpgradeRepository repository, AppUpgradeSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public AppUpgrade execute(Request request) {
        AppUpgrade appUpgrade = new AppUpgrade(
                request.getAppVersion(),
                request.getUpgradeDate(),
                request.getDownloadUrl(),
                request.getClientType(),
                request.getShowDialog(),
                request.getLocales()
        );

        repository.save(appUpgrade);
        return appUpgrade;
    }

    public static class Request extends AppUpgradeRequestBase {

    }

}
