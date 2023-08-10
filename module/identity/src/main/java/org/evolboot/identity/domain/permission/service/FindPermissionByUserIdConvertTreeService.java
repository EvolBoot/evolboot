package org.evolboot.identity.domain.permission.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.evolboot.identity.domain.permission.util.PermissionUtil;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.service.RoleQueryService;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.service.UserQueryService;
import org.evolboot.identity.domain.userrole.service.UserRoleQueryService;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Slf4j
@Service
public class FindPermissionByUserIdConvertTreeService {

    private final UserQueryService userAppService;

    private final PermissionRepository repository;

    private final UserRoleQueryService userRoleQueryService;

    private final RoleQueryService roleQueryService;

    public FindPermissionByUserIdConvertTreeService(UserQueryService userQueryService, PermissionRepository repository, UserRoleQueryService userRoleQueryService, RoleQueryService roleQueryService) {
        this.userAppService = userQueryService;
        this.repository = repository;
        this.userRoleQueryService = userRoleQueryService;
        this.roleQueryService = roleQueryService;
    }

    /**
     * 当前用户权限（树形）
     *
     * @param userId
     * @return
     */
    public List<Permission> findPermissionByUserIdConvertTree(Long userId, Type type) {
        User user = userAppService.findById(userId);
        if (user.hasUserIdentity(UserIdentity.ROLE_ADMIN)) {
            return PermissionUtil.convertTree(repository.findAll(
                    PermissionQuery.builder().type(type).build()
            ));
        }
        Set<Long> roleId = user.getRoleId();
        if (ExtendObjects.isEmpty(roleId)) {
            return Collections.emptyList();
        }
        List<Role> roles = roleQueryService.findAllById(roleId);
        if (roles.isEmpty()) {
            return Collections.emptyList();
        }
        Set<Long> collect = roles.stream().flatMap(role -> role.getPermissions().stream()).collect(Collectors.toSet());
        return PermissionUtil.convertTree(repository.findAll(
                PermissionQuery.builder().type(type).ids(collect).build()
        ));
    }

}
