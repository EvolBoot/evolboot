package org.evolboot.identity.domain.userrole;

import org.evolboot.core.data.Page;

import java.util.List;
import java.util.Set;

/**
 * 用户角色
 *
 * @author evol
 * 
 */
public interface UserRoleAppService {

    List<UserRole> findAll();

    Page<UserRole> page(UserRoleQuery query);

    UserRole findById(Long id);

    void updateRole(Long userId, Set<Long> roles);

    List<UserRole> findAll(Long userId);

}
