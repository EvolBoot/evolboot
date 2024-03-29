package org.evolboot.identity.domain.role;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.service.RoleCreateFactory;
import org.evolboot.identity.domain.role.dto.RoleQueryRequest;
import org.evolboot.identity.domain.role.service.RoleUpdateService;

import java.util.List;

/**
 * @author evol
 */
public interface RoleAppService {

    Role create(RoleCreateFactory.Request request);

    void update(RoleUpdateService.Request request);

    void delete(Long id);

    void deleteBatch(List<Long> ids);

    Page<Role> page(RoleQueryRequest query);

    List<Role> findAllById(Iterable<Long> roleIds);

    Role findById(Long id);


}
