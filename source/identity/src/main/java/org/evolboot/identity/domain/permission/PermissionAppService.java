package org.evolboot.identity.domain.permission;

import org.evolboot.core.data.Page;

import java.util.List;

/**
 * @author evol
 */
public interface PermissionAppService {


    Permission create(PermissionCreateFactory.Request request);

    Permission update(Long id, PermissionUpdateService.Request request);

    void delete(Long id);

    void deleteBatch(List<Long> ids);

    Page<Permission> page(PermissionQuery query);

    List<Permission> findAllByIdConvertTree(Iterable<Long> permissionIds);

    List<Permission> findAllById(Iterable<Long> permissionIds);

    List<Permission> findAllConvertTree();

}
