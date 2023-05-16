package org.evolboot.system.domain.appupgrade.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.system.domain.appupgrade.AppUpgrade;
import org.evolboot.system.domain.appupgrade.ClientType;

import java.util.List;
import java.util.Optional;

/**
 * APP更新
 *
 * @author evol
 */
public interface AppUpgradeRepository extends BaseRepository<AppUpgrade, Long> {

    AppUpgrade save(AppUpgrade appUpgrade);

    Optional<AppUpgrade> findById(Long id);


    void deleteById(Long id);

    List<AppUpgrade> findAll();


    AppUpgrade findFirstByClientTypeOrderByAppVersionDesc(ClientType client);


    <Q extends Query> List<AppUpgrade> findAll(Q query);

    <Q extends Query> Optional<AppUpgrade> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<AppUpgrade> page(Q query);
}
