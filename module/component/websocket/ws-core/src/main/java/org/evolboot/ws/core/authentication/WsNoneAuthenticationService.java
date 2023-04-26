package org.evolboot.ws.core.authentication;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * 校验Token
 * @author evol
 */
@Slf4j
public class WsNoneAuthenticationService implements WsAuthenticationService {


    @Override
    public String findPrincipalIdByToken(String token) {
        log.info("WS:没有认证器:使用默认的认证器:无需认证:{}", token);
        return UUID.randomUUID().toString();
    }

    @Override
    public WsAuthenticationType getType() {
        return WsAuthenticationType.TOKEN;
    }
}
