package org.evolboot.identity.domain.permission.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.service.PermissionQuery;

import java.util.List;
import java.util.Optional;


public interface PermissionRepository extends BaseRepository<Permission, Long> {

    Permission save(Permission permission);

    Optional<Permission> findById(Long permissionId);

    void deleteById(Long permissionId);

    Page<Permission> page(PermissionQuery query);

    boolean existsById(Long permissionId);

    List<Permission> findAllById(Iterable<Long> permissionIds);

    List<Permission> findAll();

    List<Permission> findAll(PermissionQuery query);

    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Permission> findOne(PermissionQuery query);

    Long count(PermissionQuery query);


}
