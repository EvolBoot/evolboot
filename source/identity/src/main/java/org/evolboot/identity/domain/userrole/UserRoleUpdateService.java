package org.evolboot.identity.domain.userrole;

import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.role.Role;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.evolboot.identity.domain.user.exception.UserRoleNotExistException;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
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
public class UserRoleUpdateService extends UserRoleSupportService {

    private final RoleRepository roleRepository;

    protected UserRoleUpdateService(UserRoleRepository repository, RoleRepository roleRepository) {
        super(repository);
        this.roleRepository = roleRepository;
    }

    public void execute(Long userId, Set<Long> roles) {
        if (ExtendObjects.isEmpty(roles)) {
            return;
        }
        requireExistRoles(roles);
        repository.deleteByUserId(userId);
        List<UserRole> userRoles = roles.stream().map(role -> new UserRole(userId, role)).collect(Collectors.toList());
        repository.saveAll(userRoles);
    }

    private void requireExistRoles(Set<Long> roleIds) {
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<Role> roles = roleRepository.findAllById(roleIds);
            if (roles.size() != roleIds.size()) {
                roleIds.removeAll(roles.stream().map(Role::getId).collect(Collectors.toList()));
                String ids = StringUtils.join(roleIds.toArray(), ",");
                throw new UserRoleNotExistException(IdentityI18nMessage.User.rolesNotFound(ids));
            }
        }
    }

}
