package org.evolboot.identity.domain.permission.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.dto.PermissionQueryRequest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface PermissionRepository extends BaseRepository<Permission, Long> {

    Permission save(Permission permission);

    Optional<Permission> findById(Long permissionId);

    void deleteById(Long permissionId);

    @Deprecated
    void deleteChildren(Long parentIds);

    List<Long> findChildren(Long parentId);

    Page<Permission> page(PermissionQueryRequest query);

    boolean existsById(Long permissionId);

    List<Permission> findAllById(Collection<Long> permissionIds);

    List<Permission> findAll();

    List<Permission> findAll(PermissionQueryRequest query);

    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Permission> findOne(PermissionQueryRequest query);

    Long count(PermissionQueryRequest query);


}
