package org.evolboot.system.domain.qa.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.system.domain.qa.entity.QQa;
import org.evolboot.system.domain.qa.entity.Qa;
import org.evolboot.system.domain.qa.service.QaQuery;
import org.evolboot.system.domain.qa.repository.QaRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * QA
 *
 * @author evol
 */
@Repository
public interface JpaQaRepository extends QaRepository, ExtendedQuerydslPredicateExecutor<Qa, Long>, JpaRepository<Qa, Long> {

    String CACHE_KEY = RedisCacheName.QA_CACHE_KEY;

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        QaQuery query = (QaQuery) _query;
        QQa q = QQa.qa;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q);
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
    default <Q extends Query> Page<Qa> page(Q query) {
        JPQLQuery<Qa> jpqlQuery = fillQueryParameter(query, QQa.qa);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<Qa> findOne(Q query) {
        return findOne(fillQueryParameter(query, QQa.qa));
    }

    @Override
    default <Q extends Query> List<Qa> findAll(Q query) {
        return findAll(fillQueryParameter(query, QQa.qa));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QQa.qa.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
