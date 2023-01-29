package org.evolboot.system.domain.appupgrade;

import org.evolboot.core.data.Page;

import java.util.List;

/**
 * APP更新
 *
 * @author evol
 */
public interface AppUpgradeAppService {

    AppUpgrade create(AppUpgradeCreateFactory.Request request);

    void update(Long id, AppUpgradeUpdateService.Request request);

    void delete(Long id);

    List<AppUpgrade> findAll();

    List<AppUpgrade> findAll(AppUpgradeQuery query);

    Page<AppUpgrade> page(AppUpgradeQuery query);

    AppUpgrade findById(Long id);

    AppUpgrade check(ClientType clientType);


}
