package org.evolboot.identity.domain.userrole.repository.jpa;

import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.identity.domain.userrole.QUserRole;
import org.evolboot.identity.domain.userrole.UserRole;
import org.evolboot.identity.domain.userrole.UserRoleQuery;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 用户角色
 *
 * @author evol
 */
@Repository
public interface JpaUserRoleRepository extends UserRoleRepository, ExtendedQuerydslPredicateExecutor<UserRole, Long>, JpaRepository<UserRole, Long> {

    default JPQLQuery<UserRole> fillQueryParameter(UserRoleQuery query) {
        QUserRole q = QUserRole.userRole;
        JPQLQuery<UserRole> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return jpqlQuery;
    }

    @Override
    default List<UserRole> findAll() {
        QUserRole q = QUserRole.userRole;
        JPQLQuery<UserRole> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default Page<UserRole> page(UserRoleQuery query) {
        return PageImpl.of(this.findAll(fillQueryParameter(query), query.toJpaPageRequest()));
    }


    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from evoltb_identity_user_role where role_id_ =?1", nativeQuery = true)
    void deleteByRoleId(Long roleId);


    @Override
    default Optional<UserRole> findOne(UserRoleQuery query) {
        return findOne(fillQueryParameter(query));
    }

}
