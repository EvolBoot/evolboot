package org.evolboot.identity.domain.role.service;

import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author evol
 */
@Service
public class RoleDeletePermissionService {

    private final RoleRepository repository;

    public RoleDeletePermissionService(RoleRepository repository) {
        this.repository = repository;
    }

    public void execute(Long permissionId) {
        List<Role> roles = repository.findByPermission(permissionId);
        for (Role role : roles) {
            role.deletePermission(permissionId);
        }
        repository.saveAll(roles);

    }
}
