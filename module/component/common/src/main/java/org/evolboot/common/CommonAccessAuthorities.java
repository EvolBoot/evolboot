package org.evolboot.common;


import org.evolboot.core.annotation.AuthorityModule;
import org.evolboot.core.annotation.AuthorityResource;

import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * @author evol
 */
@AuthorityModule(value = "common", label = "通用管理")
public interface CommonAccessAuthorities {

    @AuthorityResource(value = "config", label = "配置")
    interface Config {
        String HAS_UPDATE = AUTHORITY_PREFIX + "common_config_update" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "common_config_single" + AUTHORITY_SUFFIX;
    }

    /**
     * 字典key
     */
    @AuthorityResource(value = "dictkey", label = "字典键")
    interface DictKey {
        String HAS_CREATE = AUTHORITY_PREFIX + "common_dictkey_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "common_dictkey_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "common_dictkey_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "common_dictkey_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "common_dictkey_single" + AUTHORITY_SUFFIX;
    }

    /**
     * 字典Value
     */
    @AuthorityResource(value = "dictvalue", label = "字典值")
    interface DictValue {
        String HAS_CREATE = AUTHORITY_PREFIX + "common_dictvalue_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "common_dictvalue_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "common_dictvalue_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "common_dictvalue_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "common_dictvalue_single" + AUTHORITY_SUFFIX;
    }

}
