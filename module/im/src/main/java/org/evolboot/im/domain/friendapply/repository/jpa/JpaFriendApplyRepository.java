package org.evolboot.im.domain.friendapply.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.friendapply.FriendApplyStatus;
import org.evolboot.im.domain.friendapply.QFriendApply;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApplyQuery;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
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
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Repository
public interface JpaFriendApplyRepository extends FriendApplyRepository, ExtendedQuerydslPredicateExecutor<FriendApply>, JpaRepository<FriendApply, Long> {

    default JPQLQuery<FriendApply> fillQueryParameter(FriendApplyQuery query) {
        QFriendApply q = QFriendApply.friendApply;
        JPQLQuery<FriendApply> jpqlQuery = getJPQLQuery();
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
    default List<FriendApply> findAll(FriendApplyQuery query) {
        JPQLQuery<FriendApply> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<FriendApply> page(FriendApplyQuery query) {
        JPQLQuery<FriendApply> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }

}
