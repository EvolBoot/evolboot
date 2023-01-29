package org.evolboot.bffadmin.domain;

import org.evolboot.bffadmin.domain.repository.BffAdminMapper;
import org.evolboot.bffadmin.domain.response.BffStaffUser;
import org.evolboot.bffadmin.domain.response.BffUser;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.identity.domain.permission.Permission;
import org.evolboot.identity.domain.permission.PermissionAppService;
import org.evolboot.identity.domain.role.RoleAppService;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.identity.domain.userrole.UserRole;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
import org.evolboot.shared.lang.UserIdentity;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evol
 *
 */
@Service
@Slf4j
public class BffAdminService {

    private final BffAdminMapper mapper;
    private final UserRoleAppService userRoleAppService;
    private final RoleAppService roleAppService;
    private final PermissionAppService permissionAppService;
    private final UserAppService userAppService;
    private final BffDownloadAuthoritiesService bffDownloadAuthoritiesService;

    public BffAdminService(BffAdminMapper mapper, UserRoleAppService userRoleAppService, RoleAppService roleAppService, PermissionAppService permissionAppService, UserAppService userAppService, BffDownloadAuthoritiesService bffDownloadAuthoritiesService) {
        this.mapper = mapper;
        this.userRoleAppService = userRoleAppService;
        this.roleAppService = roleAppService;
        this.permissionAppService = permissionAppService;
        this.userAppService = userAppService;
        this.bffDownloadAuthoritiesService = bffDownloadAuthoritiesService;
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
        User user = userAppService.findByUserId(userId);
        if (user.hasUserIdentity(UserIdentity.ROLE_ADMIN)) {
            return permissionAppService.findAllConvertTree();
        }
        List<Long> roles = userRoleAppService.findAll(userId).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        Set<Long> permissionIds = Sets.newHashSet();
        roleAppService
                .findAllById(roles)
                .forEach(role -> permissionIds.addAll(role.getPermissions()));
        return permissionAppService.findAllByIdConvertTree(permissionIds);
    }

}
