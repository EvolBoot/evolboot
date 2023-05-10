package org.evolboot.system.domain.operationlog.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.system.domain.operationlog.OperationLog;
import org.evolboot.system.domain.operationlog.OperationLogQuery;
import org.evolboot.system.domain.operationlog.QOperationLog;
import org.evolboot.system.domain.operationlog.repository.OperationLogRepository;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author evol
 */
@Repository
public interface JpaOperationLogRepository extends OperationLogRepository, ExtendedQuerydslPredicateExecutor<OperationLog>, JpaRepository<OperationLog, Long> {

    default JPQLQuery<OperationLog> fillQueryParameter(OperationLogQuery query) {
        QOperationLog q = QOperationLog.operationLog;
        JPQLQuery<OperationLog> jpqlQuery = getJPQLQuery();
        return jpqlQuery;
    }


    @Override
    default Page<OperationLog> page(OperationLogQuery query) {
        QOperationLog q = QOperationLog.operationLog;
        JPQLQuery<OperationLog> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
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
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default Optional<OperationLog> findOne(OperationLogQuery query) {
        return findOne(fillQueryParameter(query));
    }

}
