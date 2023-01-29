package org.evolboot.system.domain.appupgrade.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.system.domain.appupgrade.AppUpgrade;
import org.evolboot.system.domain.appupgrade.AppUpgradeQuery;
import org.evolboot.system.domain.appupgrade.ClientType;
import org.evolboot.system.domain.appupgrade.QAppUpgrade;
import org.evolboot.system.domain.appupgrade.repository.AppUpgradeRepository;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * APP更新
 *
 * @author evol
 *
 */
@Repository
public interface JpaAppUpgradeRepository extends AppUpgradeRepository, ExtendedQuerydslPredicateExecutor<AppUpgrade>, JpaRepository<AppUpgrade, Long> {

    String CACHE_KEY = RedisCacheName.APP_UPGRADE_CACHE_KEY;

    default JPQLQuery<AppUpgrade> fillQueryParameter(AppUpgradeQuery query) {
        QAppUpgrade q = QAppUpgrade.appUpgrade;
        JPQLQuery<AppUpgrade> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return jpqlQuery;
    }

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    AppUpgrade save(AppUpgrade appUpgrade);

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteById(Long id);

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'all'")
    default List<AppUpgrade> findAll() {
        QAppUpgrade q = QAppUpgrade.appUpgrade;
        JPQLQuery<AppUpgrade> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default List<AppUpgrade> findAll(AppUpgradeQuery query) {
        JPQLQuery<AppUpgrade> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "#client.name()", unless = "#result == null")
    AppUpgrade findFirstByClientTypeOrderByAppVersionDesc(ClientType client);

    @Override
    default Page<AppUpgrade> page(AppUpgradeQuery query) {
        JPQLQuery<AppUpgrade> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
