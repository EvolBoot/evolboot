package org.evolboot.locale;


import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * @author evol
 */
public interface LocaleAccessAuthorities {


    interface Language {
        String HAS_CREATE = AUTHORITY_PREFIX + "locale_language_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "locale_language_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "locale_language_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "locale_language_page" + AUTHORITY_SUFFIX;
        String HAS_STATUS_ENABLE = AUTHORITY_PREFIX + "locale_language_status_enable" + AUTHORITY_SUFFIX;
        String HAS_STATUS_DISABLE = AUTHORITY_PREFIX + "locale_language_status_disable" + AUTHORITY_SUFFIX;
    }


    interface Region {
        String HAS_CREATE = AUTHORITY_PREFIX + "locale_region_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "locale_ountry_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "locale_region_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "locale_region_page" + AUTHORITY_SUFFIX;
        String HAS_STATUS_ENABLE = AUTHORITY_PREFIX + "locale_region_status_enable" + AUTHORITY_SUFFIX;
        String HAS_STATUS_DISABLE = AUTHORITY_PREFIX + "locale_region_status_disable" + AUTHORITY_SUFFIX;
    }


}
