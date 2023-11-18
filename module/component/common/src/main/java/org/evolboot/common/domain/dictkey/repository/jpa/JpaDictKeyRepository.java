package org.evolboot.common.domain.dictkey.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.common.domain.dictkey.entity.DictKey;
import org.evolboot.common.domain.dictkey.entity.QDictKey;
import org.evolboot.common.domain.dictkey.repository.DictKeyRepository;
import org.evolboot.common.domain.dictkey.service.DictKeyQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Repository
public interface JpaDictKeyRepository extends DictKeyRepository, ExtendedQuerydslPredicateExecutor<DictKey, Long>, JpaRepository<DictKey, Long> {

    String CACHE_KEY = RedisCacheName.DICT_CACHE_KEY;


    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    DictKey save(DictKey dictKey);

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @CacheEvict(cacheNames = CACHE_KEY, allEntries = true)
    void deleteById(Long id);

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        DictKeyQuery query = (DictKeyQuery) _query;
        QDictKey q = QDictKey.dictKey;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.sort.desc());
        jpqlQuery.select(select).from(q).orderBy(q.sort.desc());
        if (ExtendObjects.nonNull(query.getId())) {
            jpqlQuery.where(q.id.eq(query.getId()));
        }
        if (ExtendObjects.nonNull(query.getStartDate())) {
            jpqlQuery.where(q.createAt.goe(query.getStartDate()));
        }
        if (ExtendObjects.nonNull(query.getEndDate())) {
            jpqlQuery.where(q.createAt.loe(query.getEndDate()));
        }
        if (ExtendObjects.isNotBlank(query.getKey())) {
            jpqlQuery.where((q.displayName.like("%" + query.getKey() + "%")).or(q.key.like("%" + query.getKey() + "%")));
        }
        return jpqlQuery;
    }

    @Override
    default List<DictKey> findAll() {
        QDictKey q = QDictKey.dictKey;
        JPQLQuery<DictKey> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.sort.desc());
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<DictKey> page(Q query) {
        JPQLQuery<DictKey> jpqlQuery = fillQueryParameter(query, QDictKey.dictKey);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<DictKey> findOne(Q query) {
        return findOne(fillQueryParameter(query, QDictKey.dictKey));
    }

    @Override
    default <Q extends Query> List<DictKey> findAll(Q query) {
        return findAll(fillQueryParameter(query, QDictKey.dictKey));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QDictKey.dictKey.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
