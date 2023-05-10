package org.evolboot.identity.domain.permission;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.evolboot.identity.domain.permission.service.PermissionBatchDeleteService;
import org.evolboot.identity.domain.permission.service.PermissionCreateFactory;
import org.evolboot.identity.domain.permission.service.PermissionDeleteService;
import org.evolboot.identity.domain.permission.service.PermissionUpdateService;
import org.evolboot.identity.domain.permission.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class DefaultPermissionAppService implements PermissionAppService {


    private final PermissionCreateFactory permissionCreateFactory;
    private final PermissionUpdateService permissionUpdateService;
    private final PermissionBatchDeleteService permissionBatchDeleteService;
    private final PermissionRepository repository;
    private final PermissionDeleteService permissionDeleteService;

    public DefaultPermissionAppService(PermissionCreateFactory permissionCreateFactory, PermissionUpdateService permissionUpdateService, PermissionBatchDeleteService permissionBatchDeleteService, PermissionRepository repository, PermissionDeleteService permissionDeleteService) {
        this.permissionCreateFactory = permissionCreateFactory;
        this.permissionUpdateService = permissionUpdateService;
        this.permissionBatchDeleteService = permissionBatchDeleteService;
        this.repository = repository;
        this.permissionDeleteService = permissionDeleteService;
    }

    @Transactional
    @Override
    public Permission create(PermissionCreateFactory.Request request) {
        return permissionCreateFactory.create(request);
    }

    @Transactional
    @Override
    public Permission update(Long id, PermissionUpdateService.Request request) {
        return permissionUpdateService.update(id, request);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        permissionDeleteService.execute(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatch(List<Long> ids) {
        permissionBatchDeleteService.delete(ids);
    }

    @Override
    public Page<Permission> page(PermissionQuery query) {
        return repository.page(query);
    }

    @Override
    public List<Permission> findAllByIdConvertTree(Iterable<Long> permissionIds) {
        return PermissionUtil.convertTree(repository.findAllById(permissionIds));
    }

    @Override
    public List<Permission> findAllById(Iterable<Long> permissionIds) {
        return repository.findAllById(permissionIds);
    }

    @Override
    public List<Permission> findAllConvertTree() {
        return PermissionUtil.convertTree(repository.findAll());
    }

    @Override
    public Optional<Permission> findOne(PermissionQuery query) {
        return repository.findOne(query);
    }
}
