package org.evolboot.system.domain.appupgrade.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendObjects;
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
public class AppUpgradeUpdateService {

    private final AppUpgradeRepository repository;
    private final AppUpgradeSupportService supportService;

    protected AppUpgradeUpdateService(AppUpgradeRepository repository, AppUpgradeSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        AppUpgrade upgrade = supportService.findById(request.getId());
        if (ExtendObjects.isNotBlank(request.getAppVersion())) {
            upgrade.setAppVersion(request.getAppVersion());
        }
        if (ExtendObjects.isNotBlank(request.getDownloadUrl())) {
            upgrade.setDownloadUrl(request.getDownloadUrl());
        }
        if (ExtendObjects.isNotBlank(request.getUpgradeDate())) {
            upgrade.setUpgradeDate(request.getUpgradeDate());
        }
        if (ExtendObjects.nonNull(request.getClientType())) {
            upgrade.setClientType(request.getClientType());
        }
        if (ExtendObjects.nonNull(request.getShowDialog())) {
            upgrade.setShowDialog(request.getShowDialog());
        }
        if (!ExtendObjects.isEmpty(request.getLocales())) {
            upgrade.setLocales(request.getLocales());
        }
        repository.save(upgrade);
    }

    @Getter
    @Setter
    public static class Request extends AppUpgradeRequestBase {
        private Long id;
    }

}
