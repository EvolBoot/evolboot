package org.evolboot.storage;


import org.evolboot.core.annotation.AuthorityModule;
import org.evolboot.core.annotation.AuthorityResource;

import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * 权限控制标识符
 *
 * @author evol
 */
@AuthorityModule(value = "storage", label = "存储")
public interface StorageAccessAuthorities {


    /**
     *
     */
    @AuthorityResource(value = "bolb", label = "文件")
    interface Bolb {
        String HAS_CREATE = AUTHORITY_PREFIX + "storage_bolb_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "storage_bolb_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "storage_bolb_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "storage_bolb_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "storage_bolb_single" + AUTHORITY_SUFFIX;
    }
 

}
