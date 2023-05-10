package org.evolboot.system.domain.dictkey.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.system.domain.dictkey.QDictKey;
import org.evolboot.system.domain.dictkey.DictKey;
import org.evolboot.system.domain.dictkey.DictKeyQuery;
import org.evolboot.system.domain.dictkey.repository.DictKeyRepository;
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
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Repository
public interface JpaDictKeyRepository extends DictKeyRepository, ExtendedQuerydslPredicateExecutor<DictKey>, JpaRepository<DictKey, Long> {

    default JPQLQuery<DictKey> fillQueryParameter(DictKeyQuery query) {
        QDictKey q = QDictKey.dictKey;
        JPQLQuery<DictKey> jpqlQuery = getJPQLQuery();
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
    default List<DictKey> findAll(DictKeyQuery query) {
        JPQLQuery<DictKey> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<DictKey> page(DictKeyQuery query) {
        JPQLQuery<DictKey> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default Optional<DictKey> findOne(DictKeyQuery query) {
        return findOne(fillQueryParameter(query));
    }
}
