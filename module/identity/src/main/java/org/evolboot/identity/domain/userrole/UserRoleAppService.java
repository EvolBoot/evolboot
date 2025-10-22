package org.evolboot.identity.domain.userrole;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.identity.domain.userrole.dto.UserRoleQueryRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户角色(废弃不用)
 *
 * @author evol
 */
@Deprecated
public interface UserRoleAppService {

    List<UserRole> findAll();

    Page<UserRole> page(UserRoleQueryRequest query);

    UserRole findById(Long id);

    void updateRole(Long userId, Long tenantId, Set<Long> roles);

    List<UserRole> findAll(Long userId);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<UserRole> findOne(UserRoleQueryRequest query);


}
