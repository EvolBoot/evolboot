package org.evolboot.im.domain.conversation.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.dto.ConversationQueryRequest;
import org.evolboot.im.domain.conversation.entity.QConversation;
import org.evolboot.im.domain.conversation.repository.ConversationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Repository
public interface JpaConversationRepository extends ConversationRepository, ExtendedQuerydslPredicateExecutor<Conversation, Long>, JpaRepository<Conversation, Long> {


    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        ConversationQueryRequest query = (ConversationQueryRequest) _query;
        QConversation q = QConversation.conversation;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.id.desc());
        jpqlQuery.select(select).from(q);
        if (ExtendObjects.nonNull(query.getId())) {
            jpqlQuery.where(q.id.eq(query.getId()));
        }
        if (ExtendObjects.nonNull(query.getBeginAt())) {
            jpqlQuery.where(q.createAt.goe(query.getBeginAt()));
        }
        if (ExtendObjects.nonNull(query.getEndAt())) {
            jpqlQuery.where(q.createAt.loe(query.getEndAt()));
        }
        if (ExtendObjects.nonNull(query.getType())) {
            jpqlQuery.where(q.type.eq(query.getType()));
        }
        return jpqlQuery;
    }

    @Override
    default List<Conversation> findAll() {
        QConversation q = QConversation.conversation;
        JPQLQuery<Conversation> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }

    @Override
    default <Q extends Query> Page<Conversation> page(Q query) {
        JPQLQuery<Conversation> jpqlQuery = fillQueryParameter(query, QConversation.conversation);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<Conversation> findOne(Q query) {
        return findOne(fillQueryParameter(query, QConversation.conversation));
    }

    @Override
    default <Q extends Query> List<Conversation> findAll(Q query) {
        return findAll(fillQueryParameter(query, QConversation.conversation));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QConversation.conversation.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
