package org.evolboot.im.domain.groupmember.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.groupmember.QGroupMember;
import org.evolboot.im.domain.groupmember.GroupMember;
import org.evolboot.im.domain.groupmember.GroupMemberQuery;
import org.evolboot.im.domain.groupmember.repository.GroupMemberRepository;
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
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Repository
public interface JpaGroupMemberRepository extends GroupMemberRepository, ExtendedQuerydslPredicateExecutor<GroupMember>, JpaRepository<GroupMember, Long> {

    default JPQLQuery<GroupMember> fillQueryParameter(GroupMemberQuery query) {
        QGroupMember q = QGroupMember.groupMember;
        JPQLQuery<GroupMember> jpqlQuery = getJPQLQuery();
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
    default List<GroupMember> findAll() {
        QGroupMember q = QGroupMember.groupMember;
        JPQLQuery<GroupMember> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }

    @Override
    default List<GroupMember> findAll(GroupMemberQuery query) {
        JPQLQuery<GroupMember> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<GroupMember> page(GroupMemberQuery query) {
        JPQLQuery<GroupMember> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }



    @Override
    default Optional<GroupMember> findOne(GroupMemberQuery query) {
        return findOne(fillQueryParameter(query));
    }
}
