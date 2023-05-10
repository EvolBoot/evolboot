package org.evolboot.identity.domain.userrole.repository;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userrole.UserRole;
import org.evolboot.identity.domain.userrole.UserRoleQuery;

import java.util.List;
import java.util.Optional;

/**
 * 用户角色
 *
 * @author evol
 * 
 */
public interface UserRoleRepository {

    UserRole save(UserRole userRole);

    Optional<UserRole> findById(Long id);

    Page<UserRole> page(UserRoleQuery query);

    void deleteById(Long id);

    void deleteByUserId(Long userId);

    void deleteByRoleId(Long roleId);

    List<UserRole> findAll();

    List<UserRole> findAllByUserId(Long userId);

    <S extends UserRole> List<S> saveAll(Iterable<S> entities);


    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<UserRole> findOne(UserRoleQuery query);

}
