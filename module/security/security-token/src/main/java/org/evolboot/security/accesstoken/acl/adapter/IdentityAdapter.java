package org.evolboot.security.accesstoken.acl.adapter;

import com.google.common.collect.Sets;
import org.evolboot.core.domain.DelStatus;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.permission.PermissionAppService;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.role.RoleAppService;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.identity.domain.user.UserQueryService;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.entity.UserStatus;
import org.evolboot.identity.domain.user.service.UserRegisterService;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
import org.evolboot.security.accesstoken.acl.client.CaptchaClient;
import org.evolboot.security.accesstoken.acl.client.IdentityClient;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Service
public class IdentityAdapter implements IdentityClient {

    private final UserQueryService userQueryService;

    private final RoleAppService roleAppService;
    private final PermissionAppService permissionAppService;
    private final UserRoleAppService userRoleAppService;

    private final UserAppService userAppService;

    private final CaptchaClient captchaClient;


    public IdentityAdapter(UserQueryService userQueryService, RoleAppService roleAppService, PermissionAppService permissionAppService, UserRoleAppService userRoleAppService, UserAppService userAppService, CaptchaClient captchaClient) {
        this.userQueryService = userQueryService;
        this.roleAppService = roleAppService;
        this.permissionAppService = permissionAppService;
        this.userRoleAppService = userRoleAppService;
        this.userAppService = userAppService;
        this.captchaClient = captchaClient;
    }

    @Override
    public UserInfo findByUsernameOrMobileOrEmailAndEncodePassword(String value, String encodePassword) {
        User user = userQueryService.findByUsernameOrMobileOrEmailAndEncodePassword(value, encodePassword);
        Assert.isTrue(DelStatus.ACTIVE.equals(user.getDelStatus()), IdentityI18nMessage.User.userNotFound());
        Assert.isTrue(UserStatus.ACTIVE.equals(user.getStatus()), IdentityI18nMessage.User.statusNotActive());
        Set<String> authorities = getAuthorities(user);
        return new UserInfo(user.id(), user.getEnableGoogleAuth(), user.getGoogleAuthSecret(), authorities);
    }

    @Override
    public UserInfo findByMobile(String mobile) {
        User user = userQueryService.findByMobile(mobile);
        Assert.isTrue(DelStatus.ACTIVE.equals(user.getDelStatus()), IdentityI18nMessage.User.userNotFound());
        Assert.isTrue(UserStatus.ACTIVE.equals(user.getStatus()), IdentityI18nMessage.User.statusNotActive());
        Set<String> authorities = getAuthorities(user);
        return new UserInfo(user.id(), user.getEnableGoogleAuth(), user.getGoogleAuthSecret(), authorities);
    }

    @Override
    public UserInfo register(UserRegisterService.Request request) {
        User user = userAppService.register(request);
        Set<String> authorities = getAuthorities(user);
        return new UserInfo(user.id(), false, user.getGoogleAuthSecret(), authorities);
    }

    @Override
    public UserInfo findByMobileAndSmsCode(UserRegisterService.Request request) {
        if (userQueryService.existsByMobile(request.getMobile())) {
            captchaClient.verifyMobileCaptchaIsTrue(request.getMobilePrefix(), request.getMobile(), request.getCaptchaCode(), request.getCaptchaToken());
            return findByMobile(request.getMobile());
        }
        return register(request);
    }


    private Set<String> getAuthorities(User user) {
        Set<String> userIdentitys =
                user.getUserIdentity().stream()
                        .map(UserIdentity::name)
                        .collect(Collectors.toSet());
        if (!user.hasUserIdentity(UserIdentity.ROLE_STAFF)) {
            return userIdentitys;
        }
        // 只有员工需要加载权限信息
        Set<String> authorities = Sets.newHashSet();
        authorities.addAll(userIdentitys);
        Long roleId = user.getRoleId();
        if (roleId == null) {
            return authorities;
        }
        Role role = roleAppService.findById(roleId);
        if (role == null) {
            return authorities;
        }
        Set<Long> permissionIds = role.getPermissions();
        if (permissionIds == null) {
            return authorities;
        }

        List<Permission> permissions = permissionAppService.findAllById(permissionIds);
        permissions.forEach(
                permission -> authorities.addAll(List.of(permission.permSplitToArray())));
        return authorities;
    }

}
