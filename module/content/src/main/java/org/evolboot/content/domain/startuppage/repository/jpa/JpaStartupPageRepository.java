package org.evolboot.content.domain.startuppage.repository.jpa;

import org.evolboot.content.domain.startuppage.QStartupPage;
import org.evolboot.content.domain.startuppage.StartupPage;
import org.evolboot.content.domain.startuppage.StartupPageQuery;
import org.evolboot.content.domain.startuppage.repository.StartupPageRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.cache.RedisCacheName;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 启动页
 *
 * @author evol
 * 
 */
@Repository
public interface JpaStartupPageRepository extends StartupPageRepository, ExtendedQuerydslPredicateExecutor<StartupPage>, JpaRepository<StartupPage, Long> {

    String CACHE_KEY = RedisCacheName.STARTUP_PAGE_CACHE_KEY;

    default JPQLQuery<StartupPage> fillQueryParameter(StartupPageQuery query) {
        QStartupPage q = QStartupPage.startupPage;
        JPQLQuery<StartupPage> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.sort.desc());
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
    default List<StartupPage> findAll(StartupPageQuery query) {
        JPQLQuery<StartupPage> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<StartupPage> page(StartupPageQuery query) {
        JPQLQuery<StartupPage> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
