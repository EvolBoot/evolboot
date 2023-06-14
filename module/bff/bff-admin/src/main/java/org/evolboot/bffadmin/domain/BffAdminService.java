package org.evolboot.bffadmin.domain;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.bffadmin.domain.repository.BffAdminMapper;
import org.evolboot.bffadmin.domain.response.BffStaffUser;
import org.evolboot.bffadmin.domain.response.BffUser;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.identity.domain.permission.PermissionQueryService;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.role.RoleAppService;
import org.evolboot.identity.domain.user.UserQueryService;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
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
public class BffAdminService {

    private final BffAdminMapper mapper;
    private final UserRoleAppService userRoleAppService;
    private final RoleAppService roleAppService;
    private final UserAppService userAppService;
    private final UserQueryService userQueryService;
    private final BffDownloadAuthoritiesService bffDownloadAuthoritiesService;
    private final PermissionQueryService permissionQueryService;

    public BffAdminService(BffAdminMapper mapper,
                           UserRoleAppService userRoleAppService,
                           RoleAppService roleAppService,
                           UserAppService userAppService,
                           UserQueryService userQueryService,
                           BffDownloadAuthoritiesService bffDownloadAuthoritiesService,
                           PermissionQueryService permissionQueryService) {
        this.mapper = mapper;
        this.userRoleAppService = userRoleAppService;
        this.roleAppService = roleAppService;
        this.userAppService = userAppService;
        this.userQueryService = userQueryService;
        this.bffDownloadAuthoritiesService = bffDownloadAuthoritiesService;
        this.permissionQueryService = permissionQueryService;
    }

    public Page<BffUser> findUser(BffAdminQuery query) {
        return PageImpl.of(mapper.findUser(query.toMybatisPage(), query));
    }


    public Page<BffStaffUser> findStaffUser(BffAdminQuery query) {
        return PageImpl.of(mapper.findStaffUser(query.toMybatisPage(), query));
    }

    public String downloadAuthorities() {
        return bffDownloadAuthoritiesService.download();
    }


    /**
     * 当前用户权限（树形）
     *
     * @param userId
     * @return
     */
    public List<Permission> findPermissionByUserIdConvertTree(Long userId) {
        User user = userQueryService.findByUserId(userId);
        if (user.hasUserIdentity(UserIdentity.ROLE_ADMIN)) {
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
