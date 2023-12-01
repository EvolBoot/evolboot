package org.evolboot.identity.domain.permission;

import org.evolboot.core.data.Page;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.evolboot.identity.domain.permission.service.FindPermissionByUserIdConvertTreeService;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.dto.PermissionQueryRequest;


import org.evolboot.identity.domain.permission.util.PermissionUtil;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

/**
 * 查询服务 权限
 *
 * @author evol
 * @date 2023-06-14 20:14:14
 */
@Slf4j
@Service
public class PermissionQueryServiceImpl implements PermissionQueryService {

    private final PermissionRepository repository;
    private final FindPermissionByUserIdConvertTreeService findPermissionByUserIdConvertTreeService;

    protected PermissionQueryServiceImpl(PermissionRepository repository, FindPermissionByUserIdConvertTreeService findPermissionByUserIdConvertTreeService) {
        this.repository = repository;
        this.findPermissionByUserIdConvertTreeService = findPermissionByUserIdConvertTreeService;
    }


    @Override
    public List<Permission> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Permission> findAll(PermissionQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Permission> page(PermissionQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<Permission> findOne(PermissionQueryRequest query) {
        return repository.findOne(query);
    }


    @Override
    public List<Permission> findAllByIdConvertTree(Collection<Long> permissionIds) {
        return PermissionUtil.convertTree(repository.findAllById(permissionIds));
    }

    @Override
    public List<Permission> findAllById(Collection<Long> permissionIds) {
        return repository.findAllById(permissionIds);
    }

    @Override
    public List<Permission> findAllConvertTree() {
        return PermissionUtil.convertTree(repository.findAll());
    }

    @Override
    public Permission findById(Long permissionId) {
        return repository.findById(permissionId).orElseThrow(() -> new DomainNotFoundException("该权限不存在"));
    }

    @Override
    public List<Permission> findPermissionByUserIdConvertTree(Long userId, Type type) {
        return findPermissionByUserIdConvertTreeService.findPermissionByUserIdConvertTree(userId, type);
    }


}
