package org.evolboot.im.domain.conversation.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.conversation.QConversation;
import org.evolboot.im.domain.conversation.Conversation;
import org.evolboot.im.domain.conversation.ConversationQuery;
import org.evolboot.im.domain.conversation.repository.ConversationRepository;
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
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Repository
public interface JpaConversationRepository extends ConversationRepository, ExtendedQuerydslPredicateExecutor<Conversation>, JpaRepository<Conversation, Long> {

    default JPQLQuery<Conversation> fillQueryParameter(ConversationQuery query) {
        QConversation q = QConversation.conversation;
        JPQLQuery<Conversation> jpqlQuery = getJPQLQuery();
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
    default List<Conversation> findAll(ConversationQuery query) {
        JPQLQuery<Conversation> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<Conversation> page(ConversationQuery query) {
        JPQLQuery<Conversation> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default Optional<Conversation> findOne(ConversationQuery query) {
        return findOne(fillQueryParameter(query));
    }
}
