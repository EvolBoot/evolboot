package org.evolboot.identity.domain.permission;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.identity.domain.permission.service.PermissionCreateFactory;
import org.evolboot.identity.domain.permission.service.PermissionQuery;
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

    Page<Permission> page(PermissionQuery query);

    List<Permission> findAllByIdConvertTree(Collection<Long> permissionIds);

    List<Permission> findAllById(Collection<Long> permissionIds);

    List<Permission> findAllConvertTree();

    Permission findById(Long permissionId);


    /**
     * 查指定用户的菜单
     *
     * @param userId
     * @return
     */
    List<Permission> findPermissionByUserIdConvertTree(Long userId, Type type);

    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Permission> findOne(PermissionQuery query);

    List<Permission> importJsonData(String url);

}
