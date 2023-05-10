package org.evolboot.identity.domain.user.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.BitwiseExpressions;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.user.QUser;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.UserConfiguration;
import org.evolboot.identity.domain.user.UserQuery;
import org.evolboot.identity.domain.user.repository.UserIdAndInviterUserId;
import org.evolboot.identity.domain.user.repository.UserRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface JpaUserRepository extends UserRepository, ExtendedQuerydslPredicateExecutor<User>, JpaRepository<User, Long> {

    default JPQLQuery<User> fillQueryParameter(UserQuery query) {
        QUser q = QUser.user;
        JPQLQuery<User> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.asc());
        if (ExtendObjects.nonNull(query.getUserId())) {
            jpqlQuery.where(q.id.eq(query.getUserId()));
        }
        if (ExtendObjects.isNotBlank(query.getEmail())) {
            jpqlQuery.where(q.email.eq(query.getEmail().trim()));
        }
        if (ExtendObjects.isNotBlank(query.getRegisterIp())) {
            jpqlQuery.where(q.registerIp.eq(query.getRegisterIp().trim()));
        }
        if (ExtendObjects.nonNull(query.getInviterUserId())) {
            jpqlQuery.where(q.inviterUserId.eq(query.getInviterUserId()));
        }
        if (ExtendObjects.isNotBlank(query.getUsername())) {
            jpqlQuery.where(q.username.eq(query.getUsername().trim()));
        }
        if (ExtendObjects.isNotBlank(query.getMobile())) {
            jpqlQuery.where(q.mobile.eq(query.getMobile().trim()));
        }
        if (ExtendObjects.nonNull(query.getDelStatus())) {
            jpqlQuery.where(q.delStatus.eq(query.getDelStatus()));
        }
        if (ExtendObjects.nonNull(query.getUserType())) {
            jpqlQuery.where(q.userType.eq(query.getUserType()));
        }
        if (ExtendObjects.nonNull(query.getUserIdentity())) {
            jpqlQuery.where(BitwiseExpressions.bitand(q.userIdentity, query.getUserIdentity().getIdentitySymbol()).gt(0));
        }
        return jpqlQuery;
    }


    @Override
    default Page<User> page(UserQuery query) {
        JPQLQuery<User> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }

    @Override
    default Optional<User> findByUsernameOrMobileOrEmail(String value) {
        QUser q = QUser.user;
        JPQLQuery<User> jpqlQuery = getJPQLQuery();
        value = value.trim();
        jpqlQuery.select(q).from(q).where(q.username.eq(value).or(q.mobile.eq(value).or(q.email.eq(value))));
        return findOne(jpqlQuery);
    }

    @Override
    default List<UserIdAndInviterUserId> findIdAndInviterUserId() {
        QUser q = QUser.user;
        JPQLQuery<UserIdAndInviterUserId> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(
                Projections.bean(
                        UserIdAndInviterUserId.class,
                        q.id,
                        q.inviterUserId
                )
        ).from(q).orderBy(q.createAt.asc());
        return findAll(jpqlQuery);
    }

    @Override
    default String findAvatarByUserId(Long userId) {
        QUser q = QUser.user;
        JPQLQuery<String> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q.avatar).from(q).where(q.id.eq(userId));
        return findOne(jpqlQuery).orElse(UserConfiguration.getValue().getDefaultAvatar());
    }

    @Override
    default Optional<User> findOne(UserQuery query) {
        return findOne(fillQueryParameter(query));
    }
}
