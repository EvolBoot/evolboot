package org.evolboot.identity.domain.role;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.evolboot.identity.domain.role.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author evol
 */
@Service
@Slf4j
public class RoleAppServiceImpl implements RoleAppService {

    private final RoleCreateFactory roleCreateFactory;
    private final RoleUpdateService roleUpdateService;
    private final RoleRepository repository;
    private final RoleBatchDeleteService roleBatchDeleteService;
    private final RoleDeletePermissionService roleDeletePermissionService;
    private final RoleDeleteService roleDeleteService;

    public RoleAppServiceImpl(RoleCreateFactory roleCreateFactory, RoleUpdateService roleUpdateService, RoleRepository repository, RoleBatchDeleteService roleBatchDeleteService, RoleDeletePermissionService roleDeletePermissionService, RoleDeleteService roleDeleteService) {
        this.roleCreateFactory = roleCreateFactory;
        this.roleUpdateService = roleUpdateService;
        this.repository = repository;
        this.roleBatchDeleteService = roleBatchDeleteService;
        this.roleDeletePermissionService = roleDeletePermissionService;
        this.roleDeleteService = roleDeleteService;
    }

    @Transactional
    @Override
    public Role create(RoleCreateFactory.Request request) {
        return roleCreateFactory.create(request);
    }

    @Transactional
    @Override
    public void update(RoleUpdateService.Request request) {
        roleUpdateService.update(request);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        roleDeleteService.execute(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatch(List<Long> ids) {
        roleBatchDeleteService.batchDelete(ids);
    }

    @Override
    public Page<Role> page(RoleQuery query) {
        return repository.page(query);
    }

    @Override
    public List<Role> findAllById(Iterable<Long> roleIds) {
        return repository.findAllById(roleIds);
    }


}