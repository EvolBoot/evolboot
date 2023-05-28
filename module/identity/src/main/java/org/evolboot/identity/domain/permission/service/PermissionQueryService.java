package org.evolboot.identity.domain.permission.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author evol
 */
@Service
@Slf4j
public class PermissionQueryService {

    private final PermissionRepository repository;

    public PermissionQueryService(PermissionRepository repository) {
        this.repository = repository;
    }

    public List<Permission> findAllById(Collection<Long> permissionIds) {
        return repository.findAllById(permissionIds);

    }
}
