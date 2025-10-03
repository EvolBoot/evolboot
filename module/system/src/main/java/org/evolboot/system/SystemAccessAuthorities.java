package org.evolboot.system;


import org.evolboot.core.annotation.AuthorityModule;
import org.evolboot.core.annotation.AuthorityResource;

import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * 系统模块
 *
 * @author evol
 */
@AuthorityModule(value = "system", label = "系统管理")
public interface SystemAccessAuthorities {

    @AuthorityResource(value = "userloginlog", label = "用户登录日志")
    interface UserLoginLog {
        String HAS_CREATE = AUTHORITY_PREFIX + "system_userloginlog_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "system_userloginlog_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "system_userloginlog_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "system_userloginlog_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "system_userloginlog_single" + AUTHORITY_SUFFIX;
    }

    @AuthorityResource(value = "operationlog", label = "操作日志")
    interface OperationLog {
        String HAS_CREATE = AUTHORITY_PREFIX + "system_operationlog_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "system_operationlog_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "system_operationlog_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "system_operationlog_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "system_operationlog_single" + AUTHORITY_SUFFIX;
    }



}
