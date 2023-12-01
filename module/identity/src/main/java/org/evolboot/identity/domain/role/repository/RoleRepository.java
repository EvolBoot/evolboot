package org.evolboot.identity.domain.role.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.dto.RoleQueryRequest;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role, Long> {

    Role save(Role role);

    List<Role> findAll();

    <S extends Role> List<S> saveAll(Iterable<S> entities);

    Optional<Role> findById(Long roleId);

    void deleteById(Long roleId);

    Optional<Role> findByRoleName(String roleName);

    boolean existsById(Long roleId);

    List<Role> findAllById(Iterable<Long> roleIds);

    <Q extends Query> List<Role> findAll(Q query);

    <Q extends Query> Optional<Role> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<Role> page(Q query);
}
