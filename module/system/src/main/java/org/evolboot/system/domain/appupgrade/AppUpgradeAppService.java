package org.evolboot.system.domain.appupgrade;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.appupgrade.entity.AppUpgrade;
import org.evolboot.system.domain.appupgrade.entity.ClientType;
import org.evolboot.system.domain.appupgrade.service.AppUpgradeCreateFactory;
import org.evolboot.system.domain.appupgrade.service.AppUpgradeQuery;
import org.evolboot.system.domain.appupgrade.service.AppUpgradeUpdateService;

import java.util.List;
import java.util.Optional;

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


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<AppUpgrade> findOne(AppUpgradeQuery query);


}
