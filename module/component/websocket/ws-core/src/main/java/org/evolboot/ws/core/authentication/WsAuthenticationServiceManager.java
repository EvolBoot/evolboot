package org.evolboot.ws.core.authentication;

import com.google.common.collect.Maps;
import org.evolboot.core.util.Assert;

import java.util.Map;

/**
 * @author evol
 */
public final class WsAuthenticationServiceManager {

    private final static Map<WsAuthenticationType, WsAuthenticationService> wsAuthenticationServiceMap = Maps.newConcurrentMap();


    static {
        add(new WsNoneAuthenticationService());
    }

    public static void add(WsAuthenticationService wsAuthenticationService) {
        Assert.notNull(wsAuthenticationService.getType(), "WS:认证服务:Type不能为空");
        wsAuthenticationServiceMap.put(wsAuthenticationService.getType(), wsAuthenticationService);
    }

    public static WsAuthenticationService getWsAuthenticationService(WsAuthenticationType type) {
        return wsAuthenticationServiceMap.get(type);
    }

}
