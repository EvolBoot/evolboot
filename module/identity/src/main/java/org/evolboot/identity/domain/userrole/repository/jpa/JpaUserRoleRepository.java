package org.evolboot.identity.domain.userrole.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.identity.domain.userrole.QUserRole;
import org.evolboot.identity.domain.userrole.UserRole;
import org.evolboot.identity.domain.userrole.UserRoleQuery;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色
 *
 * @author evol
 */
@Repository
public interface JpaUserRoleRepository extends UserRoleRepository, ExtendedQuerydslPredicateExecutor<UserRole>, JpaRepository<UserRole, Long> {


    @Override
    default List<UserRole> findAll() {
        QUserRole q = QUserRole.userRole;
        JPQLQuery<UserRole> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default Page<UserRole> page(UserRoleQuery query) {
        QUserRole q = QUserRole.userRole;
        JPQLQuery<UserRole> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }

    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from evoltb_identity_user_role where user_id_ =?1", nativeQuery = true)
    void deleteByUserId(Long userId);

    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from evoltb_identity_user_role where role_id_ =?1", nativeQuery = true)
    void deleteByRoleId(Long roleId);



}
