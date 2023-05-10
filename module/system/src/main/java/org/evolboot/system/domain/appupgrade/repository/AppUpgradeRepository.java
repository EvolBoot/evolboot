package org.evolboot.system.domain.appupgrade.repository;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.appupgrade.AppUpgrade;
import org.evolboot.system.domain.appupgrade.AppUpgradeQuery;
import org.evolboot.system.domain.appupgrade.ClientType;

import java.util.List;
import java.util.Optional;

/**
 * APP更新
 *
 * @author evol
 *
 */
public interface AppUpgradeRepository {

    AppUpgrade save(AppUpgrade appUpgrade);

    Optional<AppUpgrade> findById(Long id);

    Page<AppUpgrade> page(AppUpgradeQuery query);

    void deleteById(Long id);

    List<AppUpgrade> findAll();

    List<AppUpgrade> findAll(AppUpgradeQuery query);

    AppUpgrade findFirstByClientTypeOrderByAppVersionDesc(ClientType client);

    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<AppUpgrade> findOne(AppUpgradeQuery query);

}
