package org.evolboot.pay.domain.receiptorder.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.pay.domain.receiptorder.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.QReceiptOrder;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderQuery;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Repository
public interface JpaReceiptOrderRepository extends ReceiptOrderRepository, ExtendedQuerydslPredicateExecutor<ReceiptOrder>, JpaRepository<ReceiptOrder, String> {

    default JPQLQuery<ReceiptOrder> fillQueryParameter(ReceiptOrderQuery query) {
        QReceiptOrder q = QReceiptOrder.receiptOrder;
        JPQLQuery<ReceiptOrder> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
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
    default List<ReceiptOrder> findAll(ReceiptOrderQuery query) {
        JPQLQuery<ReceiptOrder> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<ReceiptOrder> page(ReceiptOrderQuery query) {
        JPQLQuery<ReceiptOrder> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
