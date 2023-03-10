package org.evolboot.system.domain.userloginlog.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.system.domain.userloginlog.QUserLoginLog;
import org.evolboot.system.domain.userloginlog.UserLoginLog;
import org.evolboot.system.domain.userloginlog.UserLoginLogQuery;
import org.evolboot.system.domain.userloginlog.repository.UserLoginLogRepository;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author evol
 * 
 */
public interface JpaUserLoginLogRepository extends UserLoginLogRepository, ExtendedQuerydslPredicateExecutor<UserLoginLog>, JpaRepository<UserLoginLog, String> {


    default JPQLQuery<UserLoginLog> fillQueryParameter(UserLoginLogQuery query) {
        QUserLoginLog q = QUserLoginLog.userLoginLog;
        JPQLQuery<UserLoginLog> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.id.desc());

        if (ExtendObjects.nonNull(query.getUserId())) {
            jpqlQuery.where(q.userId.eq(query.getUserId()));
        }
        if (ExtendObjects.isNotBlank(query.getLoginIp())) {
            jpqlQuery.where(q.loginIp.eq(query.getLoginIp()));
        }
        return jpqlQuery;
    }


    @Override
    default Page<UserLoginLog> page(UserLoginLogQuery query) {
        JPQLQuery<UserLoginLog> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }
}
