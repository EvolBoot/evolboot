package org.evolboot.pay.domain.releasedorder.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.pay.domain.releasedorder.QReleasedOrder;
import org.evolboot.pay.domain.releasedorder.ReleasedOrder;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderQuery;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import com.querydsl.jpa.JPQLQuery;
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
public interface JpaReleasedOrderRepository extends ReleasedOrderRepository, ExtendedQuerydslPredicateExecutor<ReleasedOrder>, JpaRepository<ReleasedOrder, String> {

    default JPQLQuery<ReleasedOrder> fillQueryParameter(ReleasedOrderQuery query) {
        QReleasedOrder q = QReleasedOrder.releasedOrder;
        JPQLQuery<ReleasedOrder> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return jpqlQuery;
    }

    @Override
    default List<ReleasedOrder> findAll() {
        QReleasedOrder q = QReleasedOrder.releasedOrder;
        JPQLQuery<ReleasedOrder> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default List<ReleasedOrder> findAll(ReleasedOrderQuery query) {
        JPQLQuery<ReleasedOrder> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<ReleasedOrder> page(ReleasedOrderQuery query) {
        JPQLQuery<ReleasedOrder> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }

    default Optional<ReleasedOrder> findOne(ReleasedOrderQuery query) {
        return findOne(fillQueryParameter(query));
    }

}
