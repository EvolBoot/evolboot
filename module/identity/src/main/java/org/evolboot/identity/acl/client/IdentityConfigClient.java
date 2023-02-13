package org.evolboot.identity.acl.client;


import org.evolboot.configuration.domain.system.SystemConfig;

/**
 * @author evol
 */
public interface IdentityConfigClient {

    SystemConfig getSystemConfig();

    Boolean enableRegisterSms();

    Boolean enableRegisterEmailValidation();
}
