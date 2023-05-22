package org.evolboot.system.domain.startuppage.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.system.domain.startuppage.entity.QStartupPage;
import org.evolboot.system.domain.startuppage.entity.StartupPage;
import org.evolboot.system.domain.startuppage.service.StartupPageQuery;
import org.evolboot.system.domain.startuppage.repository.StartupPageRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 启动页
 *
 * @author evol
 */
@Repository
public interface JpaStartupPageRepository extends StartupPageRepository, ExtendedQuerydslPredicateExecutor<StartupPage, Long>, JpaRepository<StartupPage, Long> {

    String CACHE_KEY = RedisCacheName.STARTUP_PAGE_CACHE_KEY;

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        StartupPageQuery query = (StartupPageQuery) _query;
        QStartupPage q = QStartupPage.startupPage;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q).orderBy(q.sort.desc());
        if (ExtendObjects.nonNull(query.getEnable())) {
            jpqlQuery.where(q.enable.eq(query.getEnable()));
        }
        return jpqlQuery;
    }

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    StartupPage save(StartupPage startupPage);

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteById(Long id);

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'all'")
    default List<StartupPage> findAll() {
        QStartupPage q = QStartupPage.startupPage;
        JPQLQuery<StartupPage> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<StartupPage> page(Q query) {
        JPQLQuery<StartupPage> jpqlQuery = fillQueryParameter(query, QStartupPage.startupPage);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<StartupPage> findOne(Q query) {
        return findOne(fillQueryParameter(query, QStartupPage.startupPage));
    }

    @Override
    default <Q extends Query> List<StartupPage> findAll(Q query) {
        return findAll(fillQueryParameter(query, QStartupPage.startupPage));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QStartupPage.startupPage.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
