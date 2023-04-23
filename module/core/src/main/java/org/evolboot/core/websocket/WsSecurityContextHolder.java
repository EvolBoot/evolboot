package org.evolboot.core.websocket;

import org.springframework.util.Assert;

/**
 * @author evol
 */
public class WsSecurityContextHolder {


    private static final ThreadLocal<WsAuthentication> contextHolder = new ThreadLocal();


    public static WsAuthentication getContext() {
        return contextHolder.get();
    }

    public static void setContext(WsAuthentication context) {
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        contextHolder.set(context);
    }

}
