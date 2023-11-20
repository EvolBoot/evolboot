package org.evolboot.system;


import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * 系统模块
 *
 * @author evol
 */
public interface SystemAccessAuthorities {

    interface UserLoginLog {
        String HAS_CREATE = AUTHORITY_PREFIX + "system_userloginlog_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "system_userloginlog_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "system_userloginlog_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "system_userloginlog_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "system_userloginlog_single" + AUTHORITY_SUFFIX;
    }

    interface OperationLog {
        String HAS_CREATE = AUTHORITY_PREFIX + "identity_operationlog_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "identity_operationlog_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "identity_operationlog_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "identity_operationlog_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "identity_operationlog_single" + AUTHORITY_SUFFIX;
    }



}
