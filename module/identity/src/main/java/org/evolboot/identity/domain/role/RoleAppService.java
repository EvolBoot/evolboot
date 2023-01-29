package org.evolboot.identity.domain.role;

import org.evolboot.core.data.Page;

import java.util.List;

/**
 * @author evol
 */
public interface RoleAppService {

    Role create(RoleCreateFactory.Request request);

    void update(RoleUpdateService.Request request);

    void delete(Long id);

    void deleteBatch(List<Long> ids);

    Page<Role> page(RoleQuery query);

    List<Role> findAllById(Iterable<Long> roleIds);

}
