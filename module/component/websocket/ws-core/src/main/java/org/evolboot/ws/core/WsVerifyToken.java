package org.evolboot.ws.core;

/**
 * 校验Token
 * @author evol
 */
public interface WsVerifyToken {

    String findPrincipalIdByToken(String token);

}
