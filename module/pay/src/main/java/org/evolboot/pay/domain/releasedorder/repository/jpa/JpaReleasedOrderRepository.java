package org.evolboot.pay.domain.releasedorder.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.pay.domain.releasedorder.entity.QReleasedOrder;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderQuery;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
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
public interface JpaReleasedOrderRepository extends ReleasedOrderRepository, ExtendedQuerydslPredicateExecutor<ReleasedOrder, Long>, JpaRepository<ReleasedOrder, String> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        ReleasedOrderQuery query = (ReleasedOrderQuery) _query;
        QReleasedOrder q = QReleasedOrder.releasedOrder;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q);
        return jpqlQuery;
    }

    @Override
    default List<ReleasedOrder> findAll() {
        QReleasedOrder q = QReleasedOrder.releasedOrder;
        JPQLQuery<ReleasedOrder> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }


    default Optional<ReleasedOrder> findOne(ReleasedOrderQuery query) {
        return findOne(fillQueryParameter(query, QReleasedOrder.releasedOrder));
    }


    @Override
    default <Q extends Query> Page<ReleasedOrder> page(Q query) {
        JPQLQuery<ReleasedOrder> jpqlQuery = fillQueryParameter(query, QReleasedOrder.releasedOrder);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<ReleasedOrder> findOne(Q query) {
        return findOne(fillQueryParameter(query, QReleasedOrder.releasedOrder));
    }

    @Override
    default <Q extends Query> List<ReleasedOrder> findAll(Q query) {
        return findAll(fillQueryParameter(query, QReleasedOrder.releasedOrder));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QReleasedOrder.releasedOrder.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
