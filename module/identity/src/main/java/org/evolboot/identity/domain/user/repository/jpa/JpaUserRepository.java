package org.evolboot.identity.domain.user.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.BitwiseExpressions;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.user.QUser;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.UserConfiguration;
import org.evolboot.identity.domain.user.UserQuery;
import org.evolboot.identity.domain.user.repository.UserIdAndInviterUserId;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface JpaUserRepository extends UserRepository, ExtendedQuerydslPredicateExecutor<User, Long>, JpaRepository<User, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q query, Expression<U> select) {
        UserQuery userQuery = (UserQuery) query;
        QUser q = QUser.user;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q).orderBy(q.createAt.asc());
        if (ExtendObjects.nonNull(userQuery.getUserId())) {
            jpqlQuery.where(q.id.eq(userQuery.getUserId()));
        }
        if (ExtendObjects.isNotBlank(userQuery.getEmail())) {
            jpqlQuery.where(q.email.eq(userQuery.getEmail().trim()));
        }
        if (ExtendObjects.isNotBlank(userQuery.getRegisterIp())) {
            jpqlQuery.where(q.registerIp.eq(userQuery.getRegisterIp().trim()));
        }
        if (ExtendObjects.nonNull(userQuery.getInviterUserId())) {
            jpqlQuery.where(q.inviterUserId.eq(userQuery.getInviterUserId()));
        }
        if (ExtendObjects.isNotBlank(userQuery.getUsername())) {
            jpqlQuery.where(q.username.eq(userQuery.getUsername().trim()));
        }
        if (ExtendObjects.isNotBlank(userQuery.getMobile())) {
            jpqlQuery.where(q.mobile.eq(userQuery.getMobile().trim()));
        }
        if (ExtendObjects.nonNull(userQuery.getDelStatus())) {
            jpqlQuery.where(q.delStatus.eq(userQuery.getDelStatus()));
        }
        if (ExtendObjects.nonNull(userQuery.getUserType())) {
            jpqlQuery.where(q.userType.eq(userQuery.getUserType()));
        }
        if (ExtendObjects.nonNull(userQuery.getUserIdentity())) {
            jpqlQuery.where(BitwiseExpressions.bitand(q.userIdentity, userQuery.getUserIdentity().getIdentitySymbol()).gt(0));
        }
        return jpqlQuery;
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
    default <Q extends Query> Page<User> page(Q query) {
        JPQLQuery<User> jpqlQuery = fillQueryParameter(query, QUser.user);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<User> findOne(Q query) {
        return findOne(fillQueryParameter(query, QUser.user));
    }

    @Override
    default <Q extends Query> List<User> findAll(Q query) {
        return findAll(fillQueryParameter(query, QUser.user));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QUser.user.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
