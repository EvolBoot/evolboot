package org.evolboot.identity.domain.role.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Query;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.role.entity.QRole;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.evolboot.identity.domain.role.dto.RoleQueryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
@Repository
public interface JpaRoleRepository extends RoleRepository, ExtendedQuerydslPredicateExecutor<Role, Long>, JpaRepository<Role, Long> {


    default <U, Q extends Query> JPQLQuery<U> fillQueryParameter(Q _query, Expression<U> select) {
        RoleQueryRequest query = (RoleQueryRequest) _query;
        QRole q = QRole.role;
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
        if (ExtendObjects.isNotBlank(query.getRoleName())) {
            jpqlQuery.where(q.roleName.like("%" + query.getRoleName() + "%"));
        }
        return jpqlQuery;
    }

    @Override
    default List<Role> findAll() {
        QRole q = QRole.role;
        JPQLQuery<Role> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.createAt.desc());
        return this.findAll(jpqlQuery);
    }


    @Override
    default <Q extends Query> Page<Role> page(Q query) {
        JPQLQuery<Role> jpqlQuery = fillQueryParameter(query, QRole.role);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }


    @Override
    default <Q extends Query> Optional<Role> findOne(Q query) {
        return findOne(fillQueryParameter(query, QRole.role));
    }

    @Override
    default <Q extends Query> List<Role> findAll(Q query) {
        return findAll(fillQueryParameter(query, QRole.role));
    }

    @Override
    default <Q extends Query> long count(Q query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QRole.role.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }
}
