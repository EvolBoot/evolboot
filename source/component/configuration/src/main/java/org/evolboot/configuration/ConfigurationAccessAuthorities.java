package org.evolboot.configuration;


import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * @author evol
 */
public interface ConfigurationAccessAuthorities {

    interface System {
        String HAS_UPDATE = AUTHORITY_PREFIX + "configuration_system_update" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "configuration_system_single" + AUTHORITY_SUFFIX;
    }

}
