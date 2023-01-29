package org.evolboot.identity.acl.port;


import org.evolboot.configuration.domain.system.SystemConfig;

/**
 * @author evol
 */
public interface IdentityConfigurationPort {

    SystemConfig getSystemConfig();

    Boolean enableRegisterSms();

    Boolean enableRegisterEmailValidation();
}
