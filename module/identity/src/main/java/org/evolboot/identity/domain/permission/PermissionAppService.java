package org.evolboot.identity.domain.permission;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.service.PermissionCreateFactory;
import org.evolboot.identity.domain.permission.service.PermissionQuery;
import org.evolboot.identity.domain.permission.service.PermissionUpdateService;

import java.util.List;
import java.util.Optional;

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


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Permission> findOne(PermissionQuery query);


}
