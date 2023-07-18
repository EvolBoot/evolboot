package org.evolboot.identity.domain.user.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.BitwiseExpressions;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.domain.DelState;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.user.UserConfiguration;
import org.evolboot.identity.domain.user.entity.QUser;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.repository.UserIdAndInviterUserId;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.identity.domain.user.service.UserQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface JpaUserRepository extends UserRepository, ExtendedQuerydslPredicateExecutor<User, Long>, JpaRepository<User, Long> {

    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        UserQuery query = (UserQuery) _query;
        QUser q = QUser.user;
        JPQLQuery<U> jpqlQuery = getJPQLQuery(_query, q.createAt.desc());
        jpqlQuery.select(select).from(q);
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
        if (ExtendObjects.nonNull(query.getDelState())) {
            jpqlQuery.where(q.delState.eq(query.getDelState()));
        } else {
            jpqlQuery.where(q.delState.eq(DelState.ACTIVE));
        }
        if (ExtendObjects.nonNull(query.getUserType())) {
            jpqlQuery.where(q.userType.eq(query.getUserType()));
        }
        if (ExtendObjects.nonNull(query.getRoleId())) {
            jpqlQuery.where(q.roleId.eq(((UserQuery) _query).getRoleId()));
        }
        if (ExtendObjects.nonNull(query.getUserIdentity())) {
            jpqlQuery.where(BitwiseExpressions.bitand(q.userIdentity, query.getUserIdentity().getIdentitySymbol()).gt(0));
        }
        if (ExtendObjects.isNotBlank(query.getKey())) {
            jpqlQuery.where(q.username.like("%" + ((UserQuery) _query).getKey() + "%")
                    .or(q.nickname.like("%" + ((UserQuery) _query).getKey() + "%"))
                    .or(q.email.like("%" + ((UserQuery) _query).getKey() + "%"))
            );
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
    default List<User> findAll() {
        QUser q = QUser.user;
        JPQLQuery<User> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
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
