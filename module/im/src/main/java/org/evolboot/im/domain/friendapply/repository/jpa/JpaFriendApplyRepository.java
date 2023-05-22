package org.evolboot.im.domain.friendapply.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.service.FriendApplyQuery;
import org.evolboot.im.domain.friendapply.entity.QFriendApply;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Repository
public interface JpaFriendApplyRepository extends FriendApplyRepository, ExtendedQuerydslPredicateExecutor<FriendApply, Long>, JpaRepository<FriendApply, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        FriendApplyQuery query = (FriendApplyQuery) _query;
        QFriendApply q = QFriendApply.friendApply;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q).orderBy(q.createAt.desc());
        if (ExtendObjects.nonNull(query.getId())) {
            jpqlQuery.where(q.id.eq(query.getId()));
        }
        if (ExtendObjects.nonNull(query.getStartDate())) {
            jpqlQuery.where(q.createAt.goe(query.getStartDate()));
        }
        if (ExtendObjects.nonNull(query.getEndDate())) {
            jpqlQuery.where(q.createAt.loe(query.getEndDate()));
        }
        if (ExtendObjects.nonNull(query.getToUserId())) {
            jpqlQuery.where(q.toUserId.eq(query.getToUserId()));
        }
        return jpqlQuery;
    }

    @Override
    default List<FriendApply> findAll() {
        QFriendApply q = QFriendApply.friendApply;
        JPQLQuery<FriendApply> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<FriendApply> page(Q query) {
        JPQLQuery<FriendApply> jpqlQuery = fillQueryParameter(query, QFriendApply.friendApply);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<FriendApply> findOne(Q query) {
        return findOne(fillQueryParameter(query, QFriendApply.friendApply));
    }

    @Override
    default <Q extends Query> List<FriendApply> findAll(Q query) {
        return findAll(fillQueryParameter(query, QFriendApply.friendApply));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QFriendApply.friendApply.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
