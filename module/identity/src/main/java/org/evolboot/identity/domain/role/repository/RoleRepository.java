package org.evolboot.identity.domain.role.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.identity.domain.role.Role;
import org.evolboot.identity.domain.role.RoleQuery;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role, Long> {

    Page<Role> page(RoleQuery query);

    Role save(Role role);

    <S extends Role> List<S> saveAll(Iterable<S> entities);

    Optional<Role> findById(Long roleId);

    void deleteById(Long roleId);

    Optional<Role> findByRoleName(String roleName);

    boolean existsById(Long roleId);

    List<Role> findByPermission(Long permissionId);

    List<Role> findAllById(Iterable<Long> roleIds);
}
