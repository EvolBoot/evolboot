package org.evolboot.system.domain.operationlog.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.system.domain.operationlog.OperationLog;
import org.evolboot.system.domain.operationlog.OperationLogQuery;
import org.evolboot.system.domain.operationlog.QOperationLog;
import org.evolboot.system.domain.operationlog.repository.OperationLogRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
@Repository
public interface JpaOperationLogRepository extends OperationLogRepository, ExtendedQuerydslPredicateExecutor<OperationLog, Long>, JpaRepository<OperationLog, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        OperationLogQuery query = (OperationLogQuery) _query;
        QOperationLog q = QOperationLog.operationLog;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q);
        if (ExtendObjects.nonNull(query.getBegin())) {
            jpqlQuery.where(q.createAt.goe(query.getBegin()));
        }
        if (ExtendObjects.nonNull(query.getEnd())) {
            jpqlQuery.where(q.createAt.loe(query.getEnd()));
        }
        if (ExtendObjects.nonNull(query.getUserId())) {
            jpqlQuery.where(q.userId.eq(query.getUserId()));
        }
        if (ExtendObjects.isNotBlank(query.getOperation())) {
            jpqlQuery.where(q.operation.eq(query.getOperation()));
        }
        jpqlQuery.orderBy(q.id.desc());
        return jpqlQuery;
    }


    @Override
    default <Q extends Query> Page<OperationLog> page(Q query) {
        JPQLQuery<OperationLog> jpqlQuery = fillQueryParameter(query, QOperationLog.operationLog);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<OperationLog> findOne(Q query) {
        return findOne(fillQueryParameter(query, QOperationLog.operationLog));
    }

    @Override
    default <Q extends Query> List<OperationLog> findAll(Q query) {
        return findAll(fillQueryParameter(query, QOperationLog.operationLog));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QOperationLog.operationLog.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }


}
