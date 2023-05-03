package org.evolboot.im.domain.chatrecord.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.im.domain.chatrecord.QChatRecord;
import org.evolboot.im.domain.chatrecord.ChatRecord;
import org.evolboot.im.domain.chatrecord.ChatRecordQuery;
import org.evolboot.im.domain.chatrecord.repository.ChatRecordRepository;
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
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Repository
public interface JpaChatRecordRepository extends ChatRecordRepository, ExtendedQuerydslPredicateExecutor<ChatRecord>, JpaRepository<ChatRecord, Long> {

    default JPQLQuery<ChatRecord> fillQueryParameter(ChatRecordQuery query) {
        QChatRecord q = QChatRecord.chatRecord;
        JPQLQuery<ChatRecord> jpqlQuery = getJPQLQuery();
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
    default List<ChatRecord> findAll() {
        QChatRecord q = QChatRecord.chatRecord;
        JPQLQuery<ChatRecord> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }

    @Override
    default List<ChatRecord> findAll(ChatRecordQuery query) {
        JPQLQuery<ChatRecord> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<ChatRecord> page(ChatRecordQuery query) {
        JPQLQuery<ChatRecord> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
