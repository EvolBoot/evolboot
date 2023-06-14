package org.evolboot.identity.domain.permission;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.evolboot.identity.domain.permission.service.*;
import org.evolboot.identity.domain.permission.util.PermissionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
@Service
@Slf4j
public class PermissionAppServiceImpl implements PermissionAppService {


    private final PermissionCreateFactory permissionCreateFactory;
    private final PermissionUpdateService permissionUpdateService;
    private final PermissionRepository repository;
    private final PermissionDeleteService permissionDeleteService;

    private final FindPermissionByUserIdConvertTreeService findPermissionByUserIdConvertTreeService;

    private final PermissionImportService importService;

    public PermissionAppServiceImpl(PermissionCreateFactory permissionCreateFactory, PermissionUpdateService permissionUpdateService, PermissionRepository repository, PermissionDeleteService permissionDeleteService, FindPermissionByUserIdConvertTreeService findPermissionByUserIdConvertTreeService, PermissionImportService importService) {
        this.permissionCreateFactory = permissionCreateFactory;
        this.permissionUpdateService = permissionUpdateService;
        this.repository = repository;
        this.permissionDeleteService = permissionDeleteService;
        this.findPermissionByUserIdConvertTreeService = findPermissionByUserIdConvertTreeService;
        this.importService = importService;
    }

    @Transactional
    @Override
    public Permission create(PermissionCreateFactory.Request request) {
        return permissionCreateFactory.create(request);
    }

    @Transactional
    @Override
    public Permission update(PermissionUpdateService.Request request) {
        return permissionUpdateService.update(request);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        permissionDeleteService.execute(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatch(List<Long> ids) {
        permissionDeleteService.batchDelete(ids);
    }

    @Override
    @Transactional
    public List<Permission> importJsonData(String url) {
        importService.importJsonData(url);
        return PermissionUtil.convertTree(repository.findAll());
    }
}
