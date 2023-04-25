package org.evolboot.ws.nettysocketio.domain;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.ws.core.WsMessageSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author evol
 */
@Service
@Slf4j
public class NettyScoketIOWsMessageSender implements WsMessageSender {


    private final SocketIOServer server;

    private final SessionClientManager sessionClientManager;

    public NettyScoketIOWsMessageSender(SocketIOServer server, SessionClientManager sessionClientManager) {
        this.server = server;
        this.sessionClientManager = sessionClientManager;
    }


    /**
     * 发送消息
     *
     * @param principalId 接收的用户ID
     * @param action      接收的类型
     * @param msg         接收的消息
     */
    @Override
    public void send(String principalId, String action, Object msg) {
        List<SessionClientManager.Device> deviceList = sessionClientManager.findByPrincipalId(principalId);
        if (!ExtendObjects.isEmpty(deviceList)) {
            deviceList.forEach(device -> {
                SocketIOClient client = server.getClient(device.getSessionId());
                if (ExtendObjects.nonNull(client) && client.isChannelOpen()) {
                    client.sendEvent(action, msg);
                }
            });
        } else {
            log.info("WS:Netty-SocketIO:发送消息,对应的用户不在线:{}, {},{}", principalId, action, msg);
        }
    }

    /**
     * 广播
     *
     * @param action 接收的类型
     * @param msg    接收的消息
     */
    @Override
    public void broadcast(String action, Object msg) {
        server.getBroadcastOperations().sendEvent(action, JsonUtil.stringify(msg));
    }

    @Override
    public void printOnline() {
        log.info("WS:Netty-SocketIO:打印连接数:内存记录设备在线:{},真正设备在线:{}", sessionClientManager.getOnline(), server.getAllClients().size());
    }

    @Override
    public boolean isOnline(String principalId) {
        return sessionClientManager.isOnline(principalId);
    }

}