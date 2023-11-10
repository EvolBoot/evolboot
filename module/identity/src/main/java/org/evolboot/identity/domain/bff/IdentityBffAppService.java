package org.evolboot.identity.domain.bff;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.bff.dto.BffStaffUser;
import org.evolboot.identity.domain.bff.service.IdentityBffQuery;
import org.evolboot.identity.domain.permission.entity.Permission;

import java.util.List;

/**
 * @author evol
 */
public interface IdentityBffAppService {

    Page<BffStaffUser> findStaffUser(IdentityBffQuery query);

    String downloadAuthorities();

    List<Permission> findPermissionByUserIdConvertTree(Long userId);
}
