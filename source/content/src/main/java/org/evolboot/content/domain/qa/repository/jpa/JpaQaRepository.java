package org.evolboot.content.domain.qa.repository.jpa;

import org.evolboot.content.domain.qa.QQa;
import org.evolboot.content.domain.qa.Qa;
import org.evolboot.content.domain.qa.QaQuery;
import org.evolboot.content.domain.qa.repository.QaRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.shared.cache.RedisCacheName;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * QA
 *
 * @author evol
 * 
 */
@Repository
public interface JpaQaRepository extends QaRepository, ExtendedQuerydslPredicateExecutor<Qa>, JpaRepository<Qa, Long> {

    String CACHE_KEY = RedisCacheName.QA_CACHE_KEY;

    default JPQLQuery<Qa> fillQueryParameter(QaQuery query) {
        QQa q = QQa.qa;
        JPQLQuery<Qa> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return jpqlQuery;
    }

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    Qa save(Qa qa);

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteById(Long id);

    @Override
    @Cacheable(cacheNames = CACHE_KEY, key = "'all'")
    default List<Qa> findAll() {
        QQa q = QQa.qa;
        JPQLQuery<Qa> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default List<Qa> findAll(QaQuery query) {
        JPQLQuery<Qa> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<Qa> page(QaQuery query) {
        JPQLQuery<Qa> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
