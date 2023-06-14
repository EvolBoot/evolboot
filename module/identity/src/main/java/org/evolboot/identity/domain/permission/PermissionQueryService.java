package org.evolboot.identity.domain.permission;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.identity.domain.permission.service.PermissionQuery;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

/**
 * 查询服务 权限
 *
 * @author evol
 * @date 2023-06-14 20:14:14
 */
public interface PermissionQueryService {

    Permission findById(Long id);

    List<Permission> findAll();

    List<Permission> findAll(PermissionQuery query);

    Page<Permission> page(PermissionQuery query);


    List<Permission> findAllByIdConvertTree(Collection<Long> permissionIds);

    List<Permission> findAllById(Collection<Long> permissionIds);

    List<Permission> findAllConvertTree();


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

}
