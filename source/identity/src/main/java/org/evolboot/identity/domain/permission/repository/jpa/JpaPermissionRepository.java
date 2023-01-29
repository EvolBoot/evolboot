package org.evolboot.identity.domain.permission.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.permission.Permission;
import org.evolboot.identity.domain.permission.PermissionQuery;
import org.evolboot.identity.domain.permission.QPermission;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author evol
 */
@Repository
public interface JpaPermissionRepository extends PermissionRepository, ExtendedQuerydslPredicateExecutor<Permission>, JpaRepository<Permission, Long> {

    default JPQLQuery<Permission> fillQueryParameter(PermissionQuery query) {
        QPermission q = QPermission.permission;
        JPQLQuery<Permission> jpqlQuery = getJPQLQuery();
        if (ExtendObjects.nonNull(query.getParentId())) {
            jpqlQuery.where(q.parentId.eq(query.getParentId()));
        }
        jpqlQuery.select(q).from(q).orderBy(q.sort.desc());
        return jpqlQuery;
    }

    @Override
    default List<Permission> findAll(PermissionQuery query) {
        JPQLQuery<Permission> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }

    @Override
    default Page<Permission> page(PermissionQuery query) {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        return PageImpl.of(this.findAll(expression, query.toJpaPageRequest()));
    }
}
