package org.evolboot.im.domain.groupapply.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.groupapply.QGroupApply;
import org.evolboot.im.domain.groupapply.GroupApply;
import org.evolboot.im.domain.groupapply.GroupApplyQuery;
import org.evolboot.im.domain.groupapply.repository.GroupApplyRepository;
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
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Repository
public interface JpaGroupApplyRepository extends GroupApplyRepository, ExtendedQuerydslPredicateExecutor<GroupApply>, JpaRepository<GroupApply, Long> {

    default JPQLQuery<GroupApply> fillQueryParameter(GroupApplyQuery query) {
        QGroupApply q = QGroupApply.groupApply;
        JPQLQuery<GroupApply> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
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
    default List<GroupApply> findAll(GroupApplyQuery query) {
        JPQLQuery<GroupApply> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<GroupApply> page(GroupApplyQuery query) {
        JPQLQuery<GroupApply> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
