package org.evolboot.system.domain.dictvalue.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.system.domain.dictvalue.entity.DictValue;
import org.evolboot.system.domain.dictvalue.entity.QDictValue;
import org.evolboot.system.domain.dictvalue.repository.DictValueRepository;
import org.evolboot.system.domain.dictvalue.service.DictValueQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Repository
public interface JpaDictValueRepository extends DictValueRepository, ExtendedQuerydslPredicateExecutor<DictValue, Long>, JpaRepository<DictValue, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        DictValueQuery query = (DictValueQuery) _query;
        QDictValue q = QDictValue.dictValue;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
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
        if (ExtendObjects.nonNull(query.getDictKeyId())) {
            jpqlQuery.where(q.dictKeyId.eq(query.getDictKeyId()));
        }
        if (ExtendObjects.nonNull(query.getKey())) {
            jpqlQuery.where(q.displayName.like("%" + query.getKey() + "%").or(q.value.like("%" + query.getKey() + "%")));
        }
        return jpqlQuery;
    }

    @Override
    default List<DictValue> findAll() {
        QDictValue q = QDictValue.dictValue;
        JPQLQuery<DictValue> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.sort.desc());
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<DictValue> page(Q query) {
        JPQLQuery<DictValue> jpqlQuery = fillQueryParameter(query, QDictValue.dictValue);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<DictValue> findOne(Q query) {
        return findOne(fillQueryParameter(query, QDictValue.dictValue));
    }

    @Override
    default <Q extends Query> List<DictValue> findAll(Q query) {
        return findAll(fillQueryParameter(query, QDictValue.dictValue));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QDictValue.dictValue.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
