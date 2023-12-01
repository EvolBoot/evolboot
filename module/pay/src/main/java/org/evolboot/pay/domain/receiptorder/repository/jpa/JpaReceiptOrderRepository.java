package org.evolboot.pay.domain.receiptorder.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.pay.domain.receiptorder.entity.QReceiptOrder;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.pay.domain.receiptorder.dto.ReceiptOrderQueryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Repository
public interface JpaReceiptOrderRepository extends ReceiptOrderRepository, ExtendedQuerydslPredicateExecutor<ReceiptOrder, Long>, JpaRepository<ReceiptOrder, String> {


    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        ReceiptOrderQueryRequest query = (ReceiptOrderQueryRequest) _query;
        QReceiptOrder q = QReceiptOrder.receiptOrder;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.createAt.desc());
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
    default List<ReceiptOrder> findAll() {
        QReceiptOrder q = QReceiptOrder.receiptOrder;
        JPQLQuery<ReceiptOrder> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default <Q extends Query> Page<ReceiptOrder> page(Q query) {
        JPQLQuery<ReceiptOrder> jpqlQuery = fillQueryParameter(query, QReceiptOrder.receiptOrder);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<ReceiptOrder> findOne(Q query) {
        return findOne(fillQueryParameter(query, QReceiptOrder.receiptOrder));
    }

    @Override
    default <Q extends Query> List<ReceiptOrder> findAll(Q query) {
        return findAll(fillQueryParameter(query, QReceiptOrder.receiptOrder));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QReceiptOrder.receiptOrder.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
