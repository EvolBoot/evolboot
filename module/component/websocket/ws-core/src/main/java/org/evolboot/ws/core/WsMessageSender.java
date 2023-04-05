package org.evolboot.ws.core;

/**
 * Ws 消息发送
 * @author evol
 */
public interface WsMessageSender {

    /**
     * 主动发送消息
     * @param principalId
     * @param action
     * @param msg
     */
    void send(String principalId, String action, Object msg);

    /**
     * 广播
     * @param action
     * @param msg
     */
    void broadcast(String action, Object msg);

    /**
     * 打印在线数
     */
    void printOnline();

}
