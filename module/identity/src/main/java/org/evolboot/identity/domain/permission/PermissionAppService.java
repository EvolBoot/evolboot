package org.evolboot.identity.domain.permission;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.identity.domain.permission.service.PermissionCreateFactory;
import org.evolboot.identity.domain.permission.dto.PermissionQueryRequest;
import org.evolboot.identity.domain.permission.service.PermissionUpdateService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface PermissionAppService {


    Permission create(PermissionCreateFactory.Request request);

    Permission update(PermissionUpdateService.Request request);

    void delete(Long id);

    void deleteBatch(List<Long> ids);

    List<Permission> importJsonData(String url);

}
