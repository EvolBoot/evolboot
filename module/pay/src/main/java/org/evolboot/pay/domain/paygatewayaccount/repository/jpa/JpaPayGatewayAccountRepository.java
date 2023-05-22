package org.evolboot.pay.domain.paygatewayaccount.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.service.PayGatewayAccountQuery;
import org.evolboot.pay.domain.paygatewayaccount.entity.QPayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.repository.PayGatewayAccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Repository
public interface JpaPayGatewayAccountRepository extends PayGatewayAccountRepository, ExtendedQuerydslPredicateExecutor<PayGatewayAccount, Long>, JpaRepository<PayGatewayAccount, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        PayGatewayAccountQuery query = (PayGatewayAccountQuery) _query;
        QPayGatewayAccount q = QPayGatewayAccount.payGatewayAccount;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q);
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
    default <Q extends Query> Page<PayGatewayAccount> page(Q query) {
        JPQLQuery<PayGatewayAccount> jpqlQuery = fillQueryParameter(query, QPayGatewayAccount.payGatewayAccount);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<PayGatewayAccount> findOne(Q query) {
        return findOne(fillQueryParameter(query, QPayGatewayAccount.payGatewayAccount));
    }

    @Override
    default <Q extends Query> List<PayGatewayAccount> findAll(Q query) {
        return findAll(fillQueryParameter(query, QPayGatewayAccount.payGatewayAccount));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QPayGatewayAccount.payGatewayAccount.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }

}
