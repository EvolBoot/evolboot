package org.evolboot.system.domain.dictvalue.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.system.domain.dictvalue.QDictValue;
import org.evolboot.system.domain.dictvalue.DictValue;
import org.evolboot.system.domain.dictvalue.DictValueQuery;
import org.evolboot.system.domain.dictvalue.repository.DictValueRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.util.ExtendObjects;

import java.util.List;
import java.util.Optional;
import java.util.Date;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Repository
public interface JpaDictValueRepository extends DictValueRepository, ExtendedQuerydslPredicateExecutor<DictValue>, JpaRepository<DictValue, Long> {

    default JPQLQuery<DictValue> fillQueryParameter(DictValueQuery query) {
        QDictValue q = QDictValue.dictValue;
        JPQLQuery<DictValue> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.sort.desc());
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
            jpqlQuery.where(q.dictKeyId.loe(query.getDictKeyId()));
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
    default List<DictValue> findAll(DictValueQuery query) {
        JPQLQuery<DictValue> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<DictValue> page(DictValueQuery query) {
        JPQLQuery<DictValue> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default Optional<DictValue> findOne(DictValueQuery query) {
        return findOne(fillQueryParameter(query));
    }
}
