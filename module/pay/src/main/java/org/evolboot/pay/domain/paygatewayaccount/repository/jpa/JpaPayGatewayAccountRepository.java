package org.evolboot.pay.domain.paygatewayaccount.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQuery;
import org.evolboot.pay.domain.paygatewayaccount.QPayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Repository
public interface JpaPayGatewayAccountRepository extends PayGatewayAccountRepository, ExtendedQuerydslPredicateExecutor<PayGatewayAccount>, JpaRepository<PayGatewayAccount, Long> {

    default JPQLQuery<PayGatewayAccount> fillQueryParameter(PayGatewayAccountQuery query) {
        QPayGatewayAccount q = QPayGatewayAccount.payGatewayAccount;
        JPQLQuery<PayGatewayAccount> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        if (ExtendObjects.nonNull(query.getEnable())) {
            jpqlQuery.where(q.enable.eq(query.getEnable()));
        }
        jpqlQuery.orderBy(q.sort.desc());
        return jpqlQuery;
    }

    @Override
    default List<PayGatewayAccount> findAll() {
        QPayGatewayAccount q = QPayGatewayAccount.payGatewayAccount;
        JPQLQuery<PayGatewayAccount> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default List<PayGatewayAccount> findAll(PayGatewayAccountQuery query) {
        JPQLQuery<PayGatewayAccount> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<PayGatewayAccount> page(PayGatewayAccountQuery query) {
        JPQLQuery<PayGatewayAccount> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


}
