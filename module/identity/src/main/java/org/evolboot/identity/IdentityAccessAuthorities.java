package org.evolboot.identity;

import org.evolboot.core.annotation.AuthorityModule;
import org.evolboot.core.annotation.AuthorityResource;

import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * @author evol
 */
@AuthorityModule(value = "identity", label = "身份管理")
public interface IdentityAccessAuthorities {


    @AuthorityResource(value = "permission", label = "权限")
    interface Permission {
        String HAS_CREATE = AUTHORITY_PREFIX + "permission_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "permission_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "permission_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "permission_page" + AUTHORITY_SUFFIX;

        String TENANT_HAS_CREATE = AUTHORITY_PREFIX + "tenant_permission_create" + AUTHORITY_SUFFIX;
        String TENANT_HAS_DELETE = AUTHORITY_PREFIX + "tenant_permission_delete" + AUTHORITY_SUFFIX;
        String TENANT_HAS_UPDATE = AUTHORITY_PREFIX + "tenant_permission_update" + AUTHORITY_SUFFIX;
        String TENANT_HAS_PAGE = AUTHORITY_PREFIX + "tenant_permission_page" + AUTHORITY_SUFFIX;
    }

    @AuthorityResource(value = "role", label = "角色")
    interface Role {
        String HAS_CREATE = AUTHORITY_PREFIX + "role_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "role_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "role_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "role_page" + AUTHORITY_SUFFIX;

        String TENANT_HAS_CREATE = AUTHORITY_PREFIX + "tenant_role_create" + AUTHORITY_SUFFIX;
        String TENANT_HAS_DELETE = AUTHORITY_PREFIX + "tenant_role_delete" + AUTHORITY_SUFFIX;
        String TENANT_HAS_UPDATE = AUTHORITY_PREFIX + "tenant_role_update" + AUTHORITY_SUFFIX;
        String TENANT_HAS_PAGE = AUTHORITY_PREFIX + "tenant_role_page" + AUTHORITY_SUFFIX;
    }

    @AuthorityResource(value = "user", label = "用户")
    interface User {
        String HAS_CREATE = AUTHORITY_PREFIX + "user_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "user_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "user_update" + AUTHORITY_SUFFIX;
        String HAS_ROLE_UPDATE = AUTHORITY_PREFIX + "user_role_update" + AUTHORITY_SUFFIX;
        String HAS_PASSWORD_RESET = AUTHORITY_PREFIX + "user_password_reset" + AUTHORITY_SUFFIX;
        String HAS_LOCK = AUTHORITY_PREFIX + "user_state_lock" + AUTHORITY_SUFFIX;
        String HAS_ACTIVE = AUTHORITY_PREFIX + "user_state_active" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "user_page" + AUTHORITY_SUFFIX;

        String TENANT_HAS_CREATE = AUTHORITY_PREFIX + "tenant_user_create" + AUTHORITY_SUFFIX;
        String TENANT_HAS_DELETE = AUTHORITY_PREFIX + "tenant_user_delete" + AUTHORITY_SUFFIX;
        String TENANT_HAS_UPDATE = AUTHORITY_PREFIX + "tenant_user_update" + AUTHORITY_SUFFIX;
        String TENANT_HAS_ROLE_UPDATE = AUTHORITY_PREFIX + "tenant_user_role_update" + AUTHORITY_SUFFIX;
        String TENANT_HAS_PASSWORD_RESET = AUTHORITY_PREFIX + "tenant_user_password_reset" + AUTHORITY_SUFFIX;
        String TENANT_HAS_LOCK = AUTHORITY_PREFIX + "tenant_user_state_lock" + AUTHORITY_SUFFIX;
        String TENANT_HAS_ACTIVE = AUTHORITY_PREFIX + "tenant_user_state_active" + AUTHORITY_SUFFIX;
        String TENANT_HAS_PAGE = AUTHORITY_PREFIX + "tenant_user_page" + AUTHORITY_SUFFIX;

    }


    @AuthorityResource(value = "userrole", label = "用户角色")
    interface UserRole {
        String HAS_CREATE = AUTHORITY_PREFIX + "identity_userrole_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "identity_userrole_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "identity_userrole_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "identity_userrole_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "identity_userrole_single" + AUTHORITY_SUFFIX;
    }

    /**
     * UserId
     */
    @AuthorityResource(value = "userid", label = "用户ID")
    interface UserId {
        String HAS_CREATE = AUTHORITY_PREFIX + "identity_userid_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "identity_userid_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "identity_userid_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "identity_userid_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "identity_userid_single" + AUTHORITY_SUFFIX;
    }

}
