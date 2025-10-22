package org.evolboot.identity.domain.role;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.identity.domain.permission.entity.PermissionScope;
import org.evolboot.identity.domain.role.dto.RoleQueryRequest;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.evolboot.identity.domain.role.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author evol
 */
@Service
@Slf4j
//TODO 多语言
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
    public Page<Role> page(RoleQueryRequest query) {
        return repository.page(query);
    }

    @Override
    public List<Role> findAllByIdAndTenantId(Collection<Long> roleIds, Long tenantId) {
        return repository.findAllByIdAndTenantId(roleIds, tenantId);
    }

    @Override
    public Role findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException("找不到这个角色"));
    }

    @Override
    public Role findTenantRoleById(Long id, Long tenantId) {
        Role role = findById(id);
        requireTenantRole(role, tenantId);
        return role;
    }

    @Transactional
    @Override
    public void updateTenantRole(RoleUpdateService.Request request, Long tenantId) {
        Role role = findById(request.getId());
        requireTenantRole(role, tenantId);
        roleUpdateService.update(request);
    }

    @Transactional
    @Override
    public void deleteTenantRole(Long id, Long tenantId) {
        Role role = findById(id);
        requireTenantRole(role, tenantId);
        roleDeleteService.execute(id);
    }

    /**
     * 验证角色是否属于指定租户
     */
    private void requireTenantRole(Role role, Long tenantId) {
        if (!tenantId.equals(role.getTenantId()) || role.getScope() != PermissionScope.TENANT) {
            throw new DomainNotFoundException("角色不存在或无权限访问");
        }
    }

}