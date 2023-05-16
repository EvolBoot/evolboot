package org.evolboot.ws.core;

/**
 * Ws 消息发送
 *
 * @author evol
 */
public interface WsMessageSender {

    /**
     * 主动发送消息
     *
     * @param principalId 接收的用户ID
     * @param action      接收的类型
     * @param msg         接收的消息
     */
    void send(String principalId, String action, Object msg);

    /**
     * 广播
     *
     * @param action 接收的类型
     * @param msg    接收的消息
     */
    void broadcast(String action, Object msg);

    /**
     * 打印在线数
     */
    void printOnline();

    /**
     * 是否在线
     *
     * @param principalId
     * @return
     */
    boolean isOnline(String principalId);

}
