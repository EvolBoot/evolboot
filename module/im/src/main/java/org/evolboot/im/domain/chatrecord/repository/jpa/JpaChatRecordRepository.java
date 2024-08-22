package org.evolboot.im.domain.chatrecord.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.dto.ChatRecordQueryRequest;
import org.evolboot.im.domain.chatrecord.entity.QChatRecord;
import org.evolboot.im.domain.chatrecord.repository.ChatRecordRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Repository
public interface JpaChatRecordRepository extends ChatRecordRepository, ExtendedQuerydslPredicateExecutor<ChatRecord, Long>, JpaRepository<ChatRecord, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        ChatRecordQueryRequest query = (ChatRecordQueryRequest) _query;
        QChatRecord q = QChatRecord.chatRecord;
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
    default <Q extends Query> Page<ChatRecord> page(Q query) {
        JPQLQuery<ChatRecord> jpqlQuery = fillQueryParameter(query, QChatRecord.chatRecord);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<ChatRecord> findOne(Q query) {
        return findOne(fillQueryParameter(query, QChatRecord.chatRecord));
    }

    @Override
    default <Q extends Query> List<ChatRecord> findAll(Q query) {
        return findAll(fillQueryParameter(query, QChatRecord.chatRecord));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QChatRecord.chatRecord.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
