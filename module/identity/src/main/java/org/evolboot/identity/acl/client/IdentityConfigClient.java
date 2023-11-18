package org.evolboot.identity.acl.client;


import org.evolboot.common.domain.config.model.system.SystemConfig;

/**
 * @author evol
 */
public interface IdentityConfigClient {

    SystemConfig getSystemConfig();

    Boolean enableRegisterSms();

    Boolean enableRegisterEmailValidation();
}
