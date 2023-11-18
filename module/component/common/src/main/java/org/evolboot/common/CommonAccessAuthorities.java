package org.evolboot.common;


import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * @author evol
 */
public interface CommonAccessAuthorities {

    interface Config {
        String HAS_UPDATE = AUTHORITY_PREFIX + "common_config_update" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "common_config_single" + AUTHORITY_SUFFIX;
    }

    /**
     * 字典key
     */
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
    interface DictValue {
        String HAS_CREATE = AUTHORITY_PREFIX + "common_dictvalue_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "common_dictvalue_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "common_dictvalue_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "common_dictvalue_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "common_dictvalue_single" + AUTHORITY_SUFFIX;
    }

}
