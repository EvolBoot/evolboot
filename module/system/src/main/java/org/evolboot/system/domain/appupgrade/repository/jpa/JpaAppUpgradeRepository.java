package org.evolboot.system.domain.appupgrade.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.system.domain.appupgrade.entity.AppUpgrade;
import org.evolboot.system.domain.appupgrade.service.AppUpgradeQuery;
import org.evolboot.system.domain.appupgrade.entity.ClientType;
import org.evolboot.system.domain.appupgrade.entity.QAppUpgrade;
import org.evolboot.system.domain.appupgrade.repository.AppUpgradeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * APP更新
 *
 * @author evol
 */
@Repository
public interface JpaAppUpgradeRepository extends AppUpgradeRepository, ExtendedQuerydslPredicateExecutor<AppUpgrade, Long>, JpaRepository<AppUpgrade, Long> {

    String CACHE_KEY = RedisCacheName.APP_UPGRADE_CACHE_KEY;

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        AppUpgradeQuery query = (AppUpgradeQuery) _query;
        QAppUpgrade q = QAppUpgrade.appUpgrade;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.createAt.desc());
        jpqlQuery.select(select).from(q);
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
    @Cacheable(cacheNames = CACHE_KEY, key = "#client.name()", unless = "#result == null")
    AppUpgrade findFirstByClientTypeOrderByAppVersionDesc(ClientType client);


    @Override
    default <Q extends Query> Page<AppUpgrade> page(Q query) {
        JPQLQuery<AppUpgrade> jpqlQuery = fillQueryParameter(query, QAppUpgrade.appUpgrade);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<AppUpgrade> findOne(Q query) {
        return findOne(fillQueryParameter(query, QAppUpgrade.appUpgrade));
    }

    @Override
    default <Q extends Query> List<AppUpgrade> findAll(Q query) {
        return findAll(fillQueryParameter(query, QAppUpgrade.appUpgrade));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QAppUpgrade.appUpgrade.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
