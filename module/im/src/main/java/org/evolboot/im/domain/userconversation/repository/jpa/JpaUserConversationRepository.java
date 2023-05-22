package org.evolboot.im.domain.userconversation.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.userconversation.entity.QUserConversation;
import org.evolboot.im.domain.userconversation.entity.UserConversation;
import org.evolboot.im.domain.userconversation.service.UserConversationQuery;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Repository
public interface JpaUserConversationRepository extends UserConversationRepository, ExtendedQuerydslPredicateExecutor<UserConversation, Long>, JpaRepository<UserConversation, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        UserConversationQuery query = (UserConversationQuery) _query;
        QUserConversation q = QUserConversation.userConversation;
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
        return jpqlQuery;
    }

    @Override
    default List<UserConversation> findAll() {
        QUserConversation q = QUserConversation.userConversation;
        JPQLQuery<UserConversation> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }

    @Override
    default <Q extends Query> Page<UserConversation> page(Q query) {
        JPQLQuery<UserConversation> jpqlQuery = fillQueryParameter(query, QUserConversation.userConversation);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<UserConversation> findOne(Q query) {
        return findOne(fillQueryParameter(query, QUserConversation.userConversation));
    }

    @Override
    default <Q extends Query> List<UserConversation> findAll(Q query) {
        return findAll(fillQueryParameter(query, QUserConversation.userConversation));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QUserConversation.userConversation.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }


}
