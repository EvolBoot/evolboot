package org.evolboot.im.domain.groupapply.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.groupapply.entity.GroupApply;
import org.evolboot.im.domain.groupapply.service.GroupApplyQuery;
import org.evolboot.im.domain.groupapply.entity.QGroupApply;
import org.evolboot.im.domain.groupapply.repository.GroupApplyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Repository
public interface JpaGroupApplyRepository extends GroupApplyRepository, ExtendedQuerydslPredicateExecutor<GroupApply, Long>, JpaRepository<GroupApply, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        GroupApplyQuery query = (GroupApplyQuery) _query;
        QGroupApply q = QGroupApply.groupApply;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.createAt.desc());
        jpqlQuery.select(select).from(q);
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
    default List<GroupApply> findAll() {
        QGroupApply q = QGroupApply.groupApply;
        JPQLQuery<GroupApply> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<GroupApply> page(Q query) {
        JPQLQuery<GroupApply> jpqlQuery = fillQueryParameter(query, QGroupApply.groupApply);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<GroupApply> findOne(Q query) {
        return findOne(fillQueryParameter(query, QGroupApply.groupApply));
    }

    @Override
    default <Q extends Query> List<GroupApply> findAll(Q query) {
        return findAll(fillQueryParameter(query, QGroupApply.groupApply));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QGroupApply.groupApply.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
