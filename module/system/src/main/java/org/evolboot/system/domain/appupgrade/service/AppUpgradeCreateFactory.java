package org.evolboot.system.domain.appupgrade.service;

import org.evolboot.system.domain.appupgrade.AppUpgrade;
import org.evolboot.system.domain.appupgrade.AppUpgradeRequestBase;
import org.evolboot.system.domain.appupgrade.repository.AppUpgradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * APP更新
 *
 * @author evol
 */
@Slf4j
@Service
public class AppUpgradeCreateFactory extends AppUpgradeSupportService {
    protected AppUpgradeCreateFactory(AppUpgradeRepository repository) {
        super(repository);
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
