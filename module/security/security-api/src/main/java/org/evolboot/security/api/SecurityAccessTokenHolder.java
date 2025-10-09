package org.evolboot.security.api;

import org.evolboot.security.api.access.AccessAuthorities;
import org.evolboot.security.api.exception.AccessTokenExpiredException;
import org.evolboot.shared.lang.UserIdentity;
import org.evolboot.shared.resource.ResourceOwner;
import org.evolboot.shared.security.EvolSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Set;

/**
 * @author evol
 */
public abstract class SecurityAccessTokenHolder {

    public static EvolSession getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof EvolSession) {
            return ((EvolSession) principal);
        }
        throw new AccessTokenExpiredException(SecurityI18nMessage.accessTokenExpired());
    }

    public static String getToken() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

    public static boolean isLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof EvolSession) {
            return true;
        }
        return false;
    }


    public static boolean hasPermission(String permission) {
        if (!isLogin()) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return (authorities.contains(new SimpleGrantedAuthority(permission)) || authorities.contains(new SimpleGrantedAuthority(AccessAuthorities.ROLE_SUPER_ADMIN)));
    }

    public static void logout() {
        SecurityContextHolder.clearContext();
    }


    public static Long getUserId() {
        return getPrincipal().getUserId();
    }

    /**
     * 获取租户ID
     */
    public static Long getTenantId() {
        return getPrincipal().getTenantId();
    }

    /**
     * 判断是否为租户用户
     */
    public static boolean isTenantUser() {
        return getTenantId() != null;
    }

    /**
     * 判断是否为平台用户
     */
    public static boolean isPlatformUser() {
        return getTenantId() == null;
    }

    /**
     * 获取用户身份集合
     */
    public static Set<UserIdentity> getUserIdentities() {
        return getPrincipal().getUserIdentities();
    }

    /**
     * 判断是否拥有指定身份
     */
    public static boolean hasIdentity(UserIdentity identity) {
        Set<UserIdentity> identities = getUserIdentities();
        return identities != null && identities.contains(identity);
    }

    /**
     * 判断是否为超级管理员
     */
    public static boolean isSuperAdmin() {
        return hasIdentity(UserIdentity.ROLE_SUPER_ADMIN);
    }

    /**
     * 判断是否为租户所有者
     */
    public static boolean isTenantOwner() {
        return hasIdentity(UserIdentity.ROLE_TENANT_OWNER);
    }

    /**
     * 获取当前操作的资源归属者
     * - 租户用户创建的资源归属于租户
     * - 平台用户创建的资源归属于个人
     */
    public static ResourceOwner getResourceOwner() {
        if (isTenantUser()) {
            return ResourceOwner.tenant(getTenantId());
        }
        return ResourceOwner.user(getUserId());
    }

}
