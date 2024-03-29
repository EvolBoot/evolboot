package org.evolboot.system.domain.userloginlog.repository.jpa;

import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.system.domain.userloginlog.entity.QUserLoginLog;
import org.evolboot.system.domain.userloginlog.entity.UserLoginLog;
import org.evolboot.system.domain.userloginlog.dto.UserLoginLogQueryRequest;
import org.evolboot.system.domain.userloginlog.repository.UserLoginLogRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author evol
 */
public interface JpaUserLoginLogRepository extends UserLoginLogRepository, ExtendedQuerydslPredicateExecutor<UserLoginLog, Long>, JpaRepository<UserLoginLog, Long> {


    default JPQLQuery<UserLoginLog> fillQueryParameter(UserLoginLogQueryRequest query) {
        QUserLoginLog q = QUserLoginLog.userLoginLog;
        JPQLQuery<UserLoginLog> jpqlQuery = getJPQLQuery(query, q.id.desc());
        jpqlQuery.select(q).from(q);
        if (ExtendObjects.nonNull(query.getUserId())) {
            jpqlQuery.where(q.userId.eq(query.getUserId()));
        }
        if (ExtendObjects.isNotBlank(query.getLoginIp())) {
            jpqlQuery.where(q.loginIp.eq(query.getLoginIp()));
        }
        return jpqlQuery;
    }


    @Override
    default Page<UserLoginLog> page(UserLoginLogQueryRequest query) {
        JPQLQuery<UserLoginLog> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
