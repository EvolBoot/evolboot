package org.evolboot.configuration;


import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * @author evol
 */
public interface ConfigAccessAuthorities {

    interface System {
        String HAS_UPDATE = AUTHORITY_PREFIX + "config_system_update" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "config_system_single" + AUTHORITY_SUFFIX;
    }

}
