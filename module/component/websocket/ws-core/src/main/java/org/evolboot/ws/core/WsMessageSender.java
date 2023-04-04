package org.evolboot.ws.core;

/**
 * Ws 消息发送
 * @author evol
 */
public interface WsMessageSender {

    void send(String principalId, String action, Object msg);

    void broadcast(String action, Object msg);

}
