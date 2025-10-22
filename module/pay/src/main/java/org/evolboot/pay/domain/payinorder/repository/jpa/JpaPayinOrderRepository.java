package org.evolboot.pay.domain.payinorder.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.pay.domain.payinorder.entity.QPayinOrder;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.repository.PayinOrderRepository;
import org.evolboot.pay.domain.payinorder.dto.PayinOrderQueryRequest;
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
public interface JpaPayinOrderRepository extends PayinOrderRepository, ExtendedQuerydslPredicateExecutor<PayinOrder, Long>, JpaRepository<PayinOrder, String> {


    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        PayinOrderQueryRequest query = (PayinOrderQueryRequest) _query;
        QPayinOrder q = QPayinOrder.payinOrder;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.createAt.desc());
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
    default List<PayinOrder> findAll() {
        QPayinOrder q = QPayinOrder.payinOrder;
        JPQLQuery<PayinOrder> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default <Q extends Query> Page<PayinOrder> page(Q query) {
        JPQLQuery<PayinOrder> jpqlQuery = fillQueryParameter(query, QPayinOrder.payinOrder);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<PayinOrder> findOne(Q query) {
        return findOne(fillQueryParameter(query, QPayinOrder.payinOrder));
    }

    @Override
    default <Q extends Query> List<PayinOrder> findAll(Q query) {
        return findAll(fillQueryParameter(query, QPayinOrder.payinOrder));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QPayinOrder.payinOrder.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
