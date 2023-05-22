package org.evolboot.security.accesstoken.acl.adapter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.evolboot.core.domain.DelStatus;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.PermissionAppService;
import org.evolboot.identity.domain.role.Role;
import org.evolboot.identity.domain.role.RoleAppService;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.identity.domain.user.service.UserRegisterService;
import org.evolboot.identity.domain.user.entity.UserStatus;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
import org.evolboot.security.accesstoken.acl.client.CaptchaClient;
import org.evolboot.security.accesstoken.acl.client.IdentityClient;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Service
public class IdentityAdapter implements IdentityClient {

    private final UserAppService userAppService;

    private final RoleAppService roleAppService;
    private final PermissionAppService permissionAppService;
    private final UserRoleAppService userRoleAppService;
    private final CaptchaClient captchaClient;


    public IdentityAdapter(UserAppService userAppService, RoleAppService roleAppService, PermissionAppService permissionAppService, UserRoleAppService userRoleAppService, CaptchaClient captchaClient) {
        this.userAppService = userAppService;
        this.roleAppService = roleAppService;
        this.permissionAppService = permissionAppService;
        this.userRoleAppService = userRoleAppService;
        this.captchaClient = captchaClient;
    }

    @Override
    public UserInfo findByUsernameOrMobileOrEmailAndEncodePassword(String value, String encodePassword) {
        User user = userAppService.findByUsernameOrMobileOrEmailAndEncodePassword(value, encodePassword);
        Assert.isTrue(DelStatus.ACTIVE.equals(user.getDelStatus()), IdentityI18nMessage.User.userNotFound());
        Assert.isTrue(UserStatus.ACTIVE.equals(user.getStatus()), IdentityI18nMessage.User.statusNotActive());
        List<String> authorities = getAuthorities(user);
        return new UserInfo(user.id(), user.getEnableGoogleAuth(), user.getGoogleAuthSecret(), authorities);
    }

    @Override
    public UserInfo findByMobile(String mobile) {
        User user = userAppService.findByMobile(mobile);
        Assert.isTrue(DelStatus.ACTIVE.equals(user.getDelStatus()), IdentityI18nMessage.User.userNotFound());
        Assert.isTrue(UserStatus.ACTIVE.equals(user.getStatus()), IdentityI18nMessage.User.statusNotActive());
        List<String> authorities = getAuthorities(user);
        return new UserInfo(user.id(), user.getEnableGoogleAuth(), user.getGoogleAuthSecret(), authorities);
    }

    @Override
    public UserInfo register(UserRegisterService.Request request) {
        User user = userAppService.register(request);
        List<String> authorities = getAuthorities(user);
        return new UserInfo(user.id(), false, user.getGoogleAuthSecret(), authorities);
    }

    @Override
    public UserInfo findByMobileAndSmsCode(UserRegisterService.Request request) {
        if (userAppService.existsByMobile(request.getMobile())) {
            captchaClient.verifyMobileCaptchaIsTrue(request.getMobilePrefix(), request.getMobile(), request.getCaptchaCode(), request.getCaptchaToken());
            return findByMobile(request.getMobile());
        }
        return register(request);
    }


    private List<String> getAuthorities(User user) {
        List<String> userIdentitys =
                user.getUserIdentity().stream()
                        .map(UserIdentity::name)
                        .collect(Collectors.toList());
        if (!user.hasUserIdentity(UserIdentity.ROLE_STAFF)) {
            return userIdentitys;
        }
        // 只有员工需要加载权限信息
        List<String> authorities = Lists.newArrayList();
        authorities.addAll(userIdentitys);
        List<UserRole> userRoles = userRoleAppService.findAll(user.id());
        if (CollectionUtils.isEmpty(userRoles)) {
            return authorities;
        }
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roles = roleAppService.findAllById(roleIds);
        if (!CollectionUtils.isEmpty(roles)) {
            Set<Long> permissionIds = Sets.newHashSet();
            roles.forEach(
                    role -> {
                        permissionIds.addAll(role.getPermissions());
                    });
            if (!permissionIds.isEmpty()) {
                List<Permission> permissions = permissionAppService.findAllById(permissionIds);
                permissions.forEach(
                        permission -> authorities.addAll(List.of(permission.permSplitToArray())));
            }
        }
        return authorities;
    }

}
