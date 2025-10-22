package org.evolboot.identity.domain.userrole.service;

import org.apache.commons.lang3.StringUtils;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.service.RoleQueryService;
import org.evolboot.identity.domain.user.exception.UserRoleNotExistException;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Service
public class UserRoleUpdateService {

    private final UserRoleRepository repository;

    private final UserRoleSupportService supportService;


    private final RoleQueryService roleQueryService;

    protected UserRoleUpdateService(UserRoleRepository repository, UserRoleSupportService supportService, RoleQueryService roleQueryService) {
        this.repository = repository;
        this.supportService = supportService;
        this.roleQueryService = roleQueryService;
    }

    public void execute(Long userId, Long tenantId, Set<Long> roles) {
        if (ExtendObjects.isEmpty(roles)) {
            return;
        }
        requireExistRoles(roles, tenantId);
        repository.deleteByUserId(userId);
        List<UserRole> userRoles = roles.stream().map(role -> new UserRole(userId, role)).collect(Collectors.toList());
        repository.saveAll(userRoles);
    }

    private void requireExistRoles(Set<Long> roleIds, Long tenantId) {
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<Role> roles = roleQueryService.findAllByIdAndTenantId(roleIds, tenantId);
            if (roles.size() != roleIds.size()) {
                roleIds.removeAll(roles.stream().map(Role::getId).collect(Collectors.toList()));
                String ids = StringUtils.join(roleIds.toArray(), ",");
                throw new UserRoleNotExistException(IdentityI18nMessage.User.rolesNotFound(ids));
            }
        }
    }

}
