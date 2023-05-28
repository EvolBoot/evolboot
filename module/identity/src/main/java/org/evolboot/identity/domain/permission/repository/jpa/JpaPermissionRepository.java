package org.evolboot.identity.domain.permission.repository.jpa;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.QPermission;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.evolboot.identity.domain.permission.service.PermissionQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
@Repository
public interface JpaPermissionRepository extends PermissionRepository, ExtendedQuerydslPredicateExecutor<Permission, Long>, JpaRepository<Permission, Long> {

    default <U> JPQLQuery<U> fillQueryParameter(PermissionQuery query, Expression<U> select) {
        QPermission q = QPermission.permission;
        JPQLQuery<U> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(select).from(q).orderBy(q.sort.desc());
        if (ExtendObjects.nonNull(query.getParentId())) {
            jpqlQuery.where(q.parentId.eq(query.getParentId()));
        }
        if (ExtendObjects.nonNull(query.getParentId())) {
            jpqlQuery.where(q.parentId.eq(query.getParentId()));
        }
        if (ExtendObjects.nonNull(query.getType())) {
            jpqlQuery.where(q.type.eq(query.getType()));
        }
        if (!ExtendObjects.isEmpty(query.getIds())) {
            jpqlQuery.where(q.id.in(query.getIds()));
        }
        return jpqlQuery;
    }

    @Override
    default List<Permission> findAll(PermissionQuery query) {
        JPQLQuery<Permission> jpqlQuery = fillQueryParameter(query, QPermission.permission);
        return findAll(jpqlQuery);
    }

    @Override
    default List<Permission> findAll() {
        return findAll(PermissionQuery.builder().build());
    }

    @Override
    default Page<Permission> page(PermissionQuery query) {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        return PageImpl.of(this.findAll(expression, query.toJpaPageRequest()));
    }

    @Override
    default List<Permission> findAllById(Collection<Long> permissionIds) {
        QPermission q = QPermission.permission;
        return findAll(getJPQLQuery()
                .select(q)
                .from(q)
                .where(q.id.in(permissionIds))
                .orderBy(q.sort.desc()));
    }

    @Override
    default Optional<Permission> findOne(PermissionQuery query) {
        return findOne(fillQueryParameter(query, QPermission.permission));
    }


    @Override
    default Long count(PermissionQuery query) {
        JPQLQuery<Long> jpqlQuery = fillQueryParameter(query, QPermission.permission.id.count());
        return findOne(jpqlQuery).orElse(0L);
    }

    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from evoltb_identity_permission where JSON_CONTAINS(parent_ids_, CAST(?1 AS JSON))", nativeQuery = true)
    void deleteChildren(Long parentIds);

    @Override
    @Query(value = "select id_ from evoltb_identity_permission where JSON_CONTAINS(parent_ids_, CAST(?1 AS JSON))", nativeQuery = true)
    List<Long> findChildren(Long parentId);

}
