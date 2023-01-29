package org.evolboot.system.domain.appupgrade;

import org.evolboot.core.util.ExtendObjects;
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
public class AppUpgradeUpdateService extends AppUpgradeSupportService {
    protected AppUpgradeUpdateService(AppUpgradeRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        AppUpgrade upgrade = findById(id);
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

    public static class Request extends AppUpgradeRequestBase {
    }

}
