package org.evolboot.identity.domain.userrole;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.evolboot.identity.domain.userrole.dto.UserRoleQueryRequest;
import org.evolboot.identity.domain.userrole.service.UserRoleSupportService;
import org.evolboot.identity.domain.userrole.service.UserRoleUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户角色(废弃)
 *
 * @author evol
 */
@Slf4j
@Service
@Deprecated
public class UserRoleAppServiceImpl implements UserRoleAppService {

    private final UserRoleRepository repository;

    private final UserRoleSupportService supportService;


    private final UserRoleUpdateService updateService;

    protected UserRoleAppServiceImpl(UserRoleRepository repository, UserRoleSupportService supportService, UserRoleUpdateService updateService) {
        this.repository = repository;
        this.supportService = supportService;
        this.updateService = updateService;
    }

    @Override
    public List<UserRole> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<UserRole> page(UserRoleQueryRequest query) {
        return repository.page(query);
    }

    @Override
    public UserRole findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    @Transactional
    public void updateRole(Long userId,Long tenantId,  Set<Long> roles) {
        updateService.execute(userId,tenantId, roles);
    }


    @Override
    public List<UserRole> findAll(Long userId) {
        return repository.findAllByUserId(userId);
    }


    @Override
    public Optional<UserRole> findOne(UserRoleQueryRequest query) {
        return repository.findOne(query);
    }
}
