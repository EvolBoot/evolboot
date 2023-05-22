package org.evolboot.identity.domain.role.repository.jpa;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.service.RoleQuery;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Repository
public interface JpaRoleRepository extends RoleRepository, QuerydslPredicateExecutor<Role>, JpaRepository<Role, Long> {





    @Override
    default Page<Role> page(RoleQuery query) {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        return PageImpl.of(this.findAll(expression, query.toJpaPageRequest()));
    }

    @Override
    default List<Role> findByPermission(Long permissionId) {
        List<Role> list = findAll();
        return list.stream().filter(role -> role.getPermissions().contains(permissionId)).collect(Collectors.toList());
    }


}
