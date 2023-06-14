package org.evolboot.im.domain.friend.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.service.FriendQuery;
import org.evolboot.im.domain.friend.entity.QFriend;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Repository
public interface JpaFriendRepository extends FriendRepository, ExtendedQuerydslPredicateExecutor<Friend, Long>, JpaRepository<Friend, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        FriendQuery query = (FriendQuery) _query;
        QFriend q = QFriend.friend;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.id.desc());
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
    default List<Friend> findAll() {
        QFriend q = QFriend.friend;
        JPQLQuery<Friend> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> List<Friend> findAll(Q query) {
        JPQLQuery<Friend> jpqlQuery = fillQueryParameter(query, QFriend.friend);
        return findAll(jpqlQuery);
    }

    @Override
    default <Q extends Query> Optional<Friend> findOne(Q query) {
        return findOne(fillQueryParameter(query, QFriend.friend));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        return findOne(fillQueryParameter(query, QFriend.friend.id.count())).orElse(0L);
    }

    @Override
    default <Q extends Query> Page<Friend> page(Q query) {
        JPQLQuery<Friend> jpqlQuery = fillQueryParameter(query, QFriend.friend);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
