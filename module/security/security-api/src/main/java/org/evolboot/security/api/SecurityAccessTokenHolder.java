package org.evolboot.security.api;

import org.evolboot.security.api.access.AccessAuthorities;
import org.evolboot.security.api.exception.AccessTokenExpiredException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

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
        return (authorities.contains(new SimpleGrantedAuthority(permission)) || authorities.contains(new SimpleGrantedAuthority(AccessAuthorities.ROLE_ADMIN)));
    }

    public static void logout() {
        SecurityContextHolder.clearContext();
    }


    public static Long getPrincipalId() {
        return getPrincipal().getPrincipalId();
    }

}
