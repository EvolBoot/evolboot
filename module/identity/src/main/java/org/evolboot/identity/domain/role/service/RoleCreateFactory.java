package org.evolboot.identity.domain.role.service;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.exception.DomainRepetitionException;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.permission.entity.PermissionScope;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 *
 */
@Service
public class RoleCreateFactory {

    private final RoleRepository repository;
    private final RoleSupportService roleSupportService;

    public RoleCreateFactory(RoleRepository repository, RoleSupportService roleSupportService) {
        this.repository = repository;
        this.roleSupportService = roleSupportService;
    }

    public Role create(Request request) {
//        requireRoleNameNotRepetition(request.roleName);
        roleSupportService.requireExistPermissions(request.permissions);
        Role role = Role.builder()
                .roleName(request.roleName)
                .permissions(request.permissions)
                .scope(request.scope)
                .tenantId(request.tenantId)
                .build();
        repository.save(role);
        return role;
    }


    private void requireRoleNameNotRepetition(String roleName) {
        repository.findByRoleName(roleName).ifPresent(role -> {
            throw new DomainRepetitionException(IdentityI18nMessage.Role.roleNameRepetitionException(roleName));
        });
    }

    @Getter
    @Builder
    public static class Request {
        private String roleName;
        private Set<Long> permissions;
        private PermissionScope scope;
        private Long tenantId;
    }

}
