package org.evolboot.sentinel.acl.port;


import org.evolboot.sentinel.SentinelRole;

/**
 * @author evol
 */
public interface SentinelConfigurationPort {

    SentinelRole getSentinelRole();

    void save(SentinelRole sentinelRole);

}
