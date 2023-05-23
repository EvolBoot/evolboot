package org.evolboot.system.remote.appupgrade;

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
public class AppUpgradeListener extends AppUpgradeSupportService {

    protected AppUpgradeListener(AppUpgradeRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
