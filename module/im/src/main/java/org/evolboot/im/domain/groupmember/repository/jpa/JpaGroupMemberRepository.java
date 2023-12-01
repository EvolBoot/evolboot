package org.evolboot.im.domain.groupmember.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.groupmember.entity.GroupMember;
import org.evolboot.im.domain.groupmember.dto.GroupMemberQueryRequest;
import org.evolboot.im.domain.groupmember.entity.QGroupMember;
import org.evolboot.im.domain.groupmember.repository.GroupMemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-16 17:06:45
 */
@Repository
public interface JpaGroupMemberRepository extends GroupMemberRepository, ExtendedQuerydslPredicateExecutor<GroupMember, Long>, JpaRepository<GroupMember, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        GroupMemberQueryRequest query = (GroupMemberQueryRequest) _query;
        QGroupMember q = QGroupMember.groupMember;
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
    default List<GroupMember> findAll() {
        QGroupMember q = QGroupMember.groupMember;
        JPQLQuery<GroupMember> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<GroupMember> page(Q query) {
        JPQLQuery<GroupMember> jpqlQuery = fillQueryParameter(query, QGroupMember.groupMember);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<GroupMember> findOne(Q query) {
        return findOne(fillQueryParameter(query, QGroupMember.groupMember));
    }

    @Override
    default <Q extends Query> List<GroupMember> findAll(Q query) {
        return findAll(fillQueryParameter(query, QGroupMember.groupMember));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QGroupMember.groupMember.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
