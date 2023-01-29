package org.evolboot.identity;

import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * @author evol
 */
public interface IdentityAccessAuthorities {


    interface Permission {
        String HAS_CREATE = AUTHORITY_PREFIX + "permission_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "permission_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "permission_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "permission_page" + AUTHORITY_SUFFIX;
    }

    interface Role {
        String HAS_CREATE = AUTHORITY_PREFIX + "role_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "role_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "role_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "role_page" + AUTHORITY_SUFFIX;
    }

    interface User {
        String HAS_CREATE = AUTHORITY_PREFIX + "user_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "user_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "user_update" + AUTHORITY_SUFFIX;
        String HAS_ROLE_UPDATE = AUTHORITY_PREFIX + "user_role_update" + AUTHORITY_SUFFIX;
        String HAS_PASSWORD_RESET = AUTHORITY_PREFIX + "user_password_reset" + AUTHORITY_SUFFIX;
        String HAS_LOCK = AUTHORITY_PREFIX + "user_status_lock" + AUTHORITY_SUFFIX;
        String HAS_ACTIVE = AUTHORITY_PREFIX + "user_status_active" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "user_page" + AUTHORITY_SUFFIX;
    }


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
    interface UserId {
        String HAS_CREATE = AUTHORITY_PREFIX + "identity_userid_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "identity_userid_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "identity_userid_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "identity_userid_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "identity_userid_single" + AUTHORITY_SUFFIX;
    }

}
