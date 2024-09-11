package org.evolboot.storage;


import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * 权限控制标识符
 *
 * @author evol
 */
public interface StorageAuthorities {


    /**
     * 第三方代收订单
     */
    interface Bolb {
        String HAS_CREATE = AUTHORITY_PREFIX + "storage_bolb_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "storage_bolb_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "storage_bolb_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "storage_bolb_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "storage_bolb_single" + AUTHORITY_SUFFIX;
    }
 

}
