package org.evolboot.identity.domain.permission;


import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
public class PermissionBatchDeleteService {
    private final PermissionRepository repository;

    public PermissionBatchDeleteService(PermissionRepository repository) {
        this.repository = repository;
    }

    public void delete(List<Long> ids) {
        ids.forEach(repository::deleteById);
    }
}
