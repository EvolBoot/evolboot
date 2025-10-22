package org.evolboot.pay.domain.payoutorder.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.pay.domain.payoutorder.entity.QPayoutOrder;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrder;
import org.evolboot.pay.domain.payoutorder.repository.PayoutOrderRepository;
import org.evolboot.pay.domain.payoutorder.dto.PayoutOrderQueryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 代付订单
 *
 * @author evol
 */
@Repository
public interface JpaPayoutOrderRepository extends PayoutOrderRepository, ExtendedQuerydslPredicateExecutor<PayoutOrder, Long>, JpaRepository<PayoutOrder, String> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        PayoutOrderQueryRequest query = (PayoutOrderQueryRequest) _query;
        QPayoutOrder q = QPayoutOrder.payoutOrder;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.createAt.desc());
        jpqlQuery.select(select).from(q);
        return jpqlQuery;
    }

    @Override
    default List<PayoutOrder> findAll() {
        QPayoutOrder q = QPayoutOrder.payoutOrder;
        JPQLQuery<PayoutOrder> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }


    default Optional<PayoutOrder> findOne(PayoutOrderQueryRequest query) {
        return findOne(fillQueryParameter(query, QPayoutOrder.payoutOrder));
    }


    @Override
    default <Q extends Query> Page<PayoutOrder> page(Q query) {
        JPQLQuery<PayoutOrder> jpqlQuery = fillQueryParameter(query, QPayoutOrder.payoutOrder);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<PayoutOrder> findOne(Q query) {
        return findOne(fillQueryParameter(query, QPayoutOrder.payoutOrder));
    }

    @Override
    default <Q extends Query> List<PayoutOrder> findAll(Q query) {
        return findAll(fillQueryParameter(query, QPayoutOrder.payoutOrder));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QPayoutOrder.payoutOrder.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
