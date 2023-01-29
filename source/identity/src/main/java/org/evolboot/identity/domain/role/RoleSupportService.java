package org.evolboot.identity.domain.role;

import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.permission.Permission;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evol
 *
 */
@Service
public class RoleSupportService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleSupportService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public void requireExistPermissions(Set<Long> permissionIds) {
        if (!CollectionUtils.isEmpty(permissionIds)) {
            List<Permission> permissions = permissionRepository.findAllById(permissionIds);
            if (permissions.size() != permissionIds.size()) {
                permissionIds.removeAll(permissions.stream().map(Permission::getId).collect(Collectors.toList()));
                String ids = StringUtils.join(permissionIds.toArray(), ",");
                throw new RolePermissionNotExistException(IdentityI18nMessage.Role.permissionsNotFound(ids));
            }
        }
    }

}
