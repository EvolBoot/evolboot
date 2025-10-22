package org.evolboot.bff;


import org.evolboot.core.annotation.AuthorityModule;
import org.evolboot.core.annotation.AuthorityResource;

import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * @author evol
 */
@AuthorityModule(value = "Bff", label = "综合")
public interface BffAdminAccessAuthorities {

    @AuthorityResource(value = "bffAdminAuthorityResource", label = "综合权限")
    interface BffAdminAuthorityResource {
        String HAS_BFF_AUTHORITY = AUTHORITY_PREFIX + "bff_authority" + AUTHORITY_SUFFIX;
    }

}
