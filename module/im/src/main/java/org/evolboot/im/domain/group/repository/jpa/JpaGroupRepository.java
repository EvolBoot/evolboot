package org.evolboot.im.domain.group.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.group.QGroup;
import org.evolboot.im.domain.group.Group;
import org.evolboot.im.domain.group.GroupQuery;
import org.evolboot.im.domain.group.repository.GroupRepository;
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
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Repository
public interface JpaGroupRepository extends GroupRepository, ExtendedQuerydslPredicateExecutor<Group>, JpaRepository<Group, Long> {

    default JPQLQuery<Group> fillQueryParameter(GroupQuery query) {
        QGroup q = QGroup.group;
        JPQLQuery<Group> jpqlQuery = getJPQLQuery();
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
    default List<Group> findAll() {
        QGroup q = QGroup.group;
        JPQLQuery<Group> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }

    @Override
    default List<Group> findAll(GroupQuery query) {
        JPQLQuery<Group> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<Group> page(GroupQuery query) {
        JPQLQuery<Group> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
