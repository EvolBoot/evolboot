package org.evolboot.identity.domain.role.service;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.exception.DomainRepetitionException;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class RoleUpdateService {

    private final RoleRepository repository;
    private final RoleSupportService roleSupportService;

    public RoleUpdateService(RoleRepository repository, RoleSupportService roleSupportService) {
        this.repository = repository;
        this.roleSupportService = roleSupportService;
    }

    public void update(Request request) {
        Role role = repository.findById(request.getId()).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.Role.roleNotFound()));
        requireRoleNameNotRepetition(request.roleName, request.id);
        roleSupportService.requireExistPermissions(request.permissions);
        role.update(request.roleName, request.permissions, request.remark);
        repository.save(role);
    }


    private void requireRoleNameNotRepetition(String roleName, Long roleId) {
        repository.findByRoleName(roleName).ifPresent(role -> {
            if (!role.getId().equals(roleId)) {
                throw new DomainRepetitionException(IdentityI18nMessage.Role.roleNameRepetitionException(roleName));
            }
        });
    }


    @Getter
    @Setter
    public static class Request {
        private Long id;
        private String roleName;
        private String remark;
        private Set<Long> permissions;
    }


}
