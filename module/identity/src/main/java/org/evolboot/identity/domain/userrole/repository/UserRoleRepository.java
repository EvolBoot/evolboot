package org.evolboot.identity.domain.userrole.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.identity.domain.userrole.dto.UserRoleQueryRequest;

import java.util.List;
import java.util.Optional;

/**
 * 用户角色
 *
 * @author evol
 */
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

    UserRole save(UserRole userRole);

    Optional<UserRole> findById(Long id);

    Page<UserRole> page(UserRoleQueryRequest query);

    void deleteById(Long id);

    void deleteByUserId(Long userId);

    void deleteByRoleId(Long roleId);

    List<UserRole> findAll();

    List<UserRole> findAllByUserId(Long userId);

    <S extends UserRole> List<S> saveAll(Iterable<S> entities);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<UserRole> findOne(UserRoleQueryRequest query);

}
