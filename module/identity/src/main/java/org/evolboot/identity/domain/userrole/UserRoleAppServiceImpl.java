package org.evolboot.identity.domain.userrole;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.evolboot.identity.domain.userrole.service.UserRoleQuery;
import org.evolboot.identity.domain.userrole.service.UserRoleSupportService;
import org.evolboot.identity.domain.userrole.service.UserRoleUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户角色
 *
 * @author evol
 */
@Slf4j
@Service
public class UserRoleAppServiceImpl extends UserRoleSupportService implements UserRoleAppService {


    private final UserRoleUpdateService updateService;

    protected UserRoleAppServiceImpl(UserRoleRepository repository, UserRoleUpdateService updateService) {
        super(repository);
        this.updateService = updateService;
    }

    @Override
    public List<UserRole> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<UserRole> page(UserRoleQuery query) {
        return repository.page(query);
    }

    @Override
    @Transactional
    public void updateRole(Long userId, Set<Long> roles) {
        updateService.execute(userId, roles);
    }


    @Override
    public List<UserRole> findAll(Long userId) {
        return repository.findAllByUserId(userId);
    }


    @Override
    public Optional<UserRole> findOne(UserRoleQuery query) {
        return repository.findOne(query);
    }
}
