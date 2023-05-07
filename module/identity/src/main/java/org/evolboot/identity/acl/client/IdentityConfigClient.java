package org.evolboot.identity.acl.client;


import org.evolboot.configuration.domain.config.system.SystemConfig;

/**
 * @author evol
 */
public interface IdentityConfigClient {

    SystemConfig getSystemConfig();

    Boolean enableRegisterSms();

    Boolean enableRegisterEmailValidation();
}
