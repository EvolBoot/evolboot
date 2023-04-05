package org.evolboot.ws.core.test;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.event.ws.WsConnectedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class WsTestListener {

    @EventListener
    public void on(WsConnectedEvent event) {
        log.info("连接:{},{}", event.getDeviceType(), event.getPrincipalId());
    }
}
