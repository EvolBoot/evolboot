package org.evolboot.ws.core.authentication;

/**
 * 校验Token
 *
 * @author evol
 */
public interface WsAuthenticationService {

    String findPrincipalIdByToken(String token);

    WsAuthenticationType getType();

}
