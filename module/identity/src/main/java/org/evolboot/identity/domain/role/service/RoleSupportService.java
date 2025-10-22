package org.evolboot.identity.domain.role.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.service.PermissionQueryService;
import org.evolboot.identity.domain.role.exception.RolePermissionNotExistException;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Slf4j
@Service
public class RoleSupportService {
    protected final RoleRepository roleRepository;
    private final PermissionQueryService permissionQueryService;

    public RoleSupportService(RoleRepository roleRepository, PermissionQueryService permissionQueryService) {
        this.roleRepository = roleRepository;
        this.permissionQueryService = permissionQueryService;
    }

    public void requireExistPermissions(Set<Long> permissionIds) {
        if (!CollectionUtils.isEmpty(permissionIds)) {
            List<Permission> permissions = permissionQueryService.findAllById(permissionIds);
            if (permissions.size() != permissionIds.size()) {
                permissions.stream().map(Permission::getId).toList().forEach(permissionIds::remove);
                String ids = StringUtils.join(permissionIds.toArray(), ",");
                throw new RolePermissionNotExistException(IdentityI18nMessage.Role.permissionsNotFound(ids));
            }
        }
    }

}
