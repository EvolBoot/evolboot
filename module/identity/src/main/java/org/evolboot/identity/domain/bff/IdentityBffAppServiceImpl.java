package org.evolboot.identity.domain.bff;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.identity.domain.bff.dto.BffStaffUser;
import org.evolboot.identity.domain.bff.repository.IdentityBffMapper;
import org.evolboot.identity.domain.bff.service.IdentityBffDownloadAuthoritiesService;
import org.evolboot.identity.domain.bff.dto.IdentityBffQueryRequest;
import org.evolboot.identity.domain.permission.PermissionQueryService;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.role.RoleAppService;
import org.evolboot.identity.domain.user.UserQueryService;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Service
@Slf4j
public class IdentityBffAppServiceImpl implements IdentityBffAppService {

    private final IdentityBffMapper mapper;

    private final IdentityBffDownloadAuthoritiesService downloadAuthoritiesService;

    private final UserQueryService userQueryService;

    private final PermissionQueryService permissionQueryService;

    private final UserRoleAppService userRoleAppService;

    private final RoleAppService roleAppService;

    public IdentityBffAppServiceImpl(IdentityBffMapper mapper, IdentityBffDownloadAuthoritiesService downloadAuthoritiesService, UserQueryService userQueryService, PermissionQueryService permissionQueryService, UserRoleAppService userRoleAppService, RoleAppService roleAppService) {
        this.mapper = mapper;
        this.downloadAuthoritiesService = downloadAuthoritiesService;
        this.userQueryService = userQueryService;
        this.permissionQueryService = permissionQueryService;
        this.userRoleAppService = userRoleAppService;
        this.roleAppService = roleAppService;
    }

    public Page<BffStaffUser> findStaffUser(IdentityBffQueryRequest query) {
        return PageImpl.of(mapper.findStaffUser(query.toMybatisPage(), query));
    }

    @Override
    public String downloadAuthorities() {
        return downloadAuthoritiesService.download();
    }

    @Override
    public List<Permission> findPermissionByUserIdConvertTree(Long userId) {
        User user = userQueryService.findByUserId(userId);
        if (user.hasUserIdentity(UserIdentity.ROLE_SUPER_ADMIN)) {
            return permissionQueryService.findAllConvertTree();
        }
        List<Long> roles = userRoleAppService.findAll(userId).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        Set<Long> permissionIds = Sets.newHashSet();
        roleAppService
                .findAllById(roles)
                .forEach(role -> permissionIds.addAll(role.getPermissions()));
        return permissionQueryService.findAllByIdConvertTree(permissionIds);
    }


}
