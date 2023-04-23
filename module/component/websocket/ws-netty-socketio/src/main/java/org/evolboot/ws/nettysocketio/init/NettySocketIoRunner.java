package org.evolboot.ws.nettysocketio.init;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.event.ws.WsConnectedEvent;
import org.evolboot.shared.event.ws.WsDisconnectdEvent;
import org.evolboot.shared.lang.DeviceType;
import org.evolboot.ws.core.WsMessageHandle;
import org.evolboot.ws.core.WsMessageSender;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author evol
 */
@Service
@Slf4j
public class NettySocketIoRunner implements CommandLineRunner, DisposableBean, WsMessageSender {

    private final static String connectedAction = "connect";

    private final EventPublisher eventPublisher;

    private final Map<String, List<UUID>> principalSocketClient = Maps.newConcurrentMap();
    private final Map<String, String> sessionIdAndPrincipalId = Maps.newConcurrentMap();


    private final WsMessageHandle wsMessageHandle;

    private SocketIOServer server;
//    private SocketIONamespace messageClient;

    public NettySocketIoRunner(EventPublisher eventPublisher, WsMessageHandle wsMessageHandle) {
        this.eventPublisher = eventPublisher;
        this.wsMessageHandle = wsMessageHandle;
    }

    @Override
    public void run(String... args) throws Exception {
        Configuration config = new Configuration();

        //TODO 配置
        config.setHostname("localhost");
        config.setPort(9092);
        server = new SocketIOServer(config);
//        messageClient = server.addNamespace("/ws");

        server.addConnectListener(client -> {
            String token = getToken(client);
            DeviceType deviceType = getDeviceType(client);
            log.info("WS:Netty-SocketIO:连接:{},{},{}", token, client.getSessionId(), deviceType);
            if (ExtendObjects.isBlank(token)) {
                client.disconnect();
                log.info("WS:Netty-SocketIO:连接:没有Token:断开连接:{}", client.isChannelOpen());
                return;
            }
            //TODO 校验 token 和设备类型
            String principalId = token;
            List<UUID> uuids = principalSocketClient.get(principalId);
            if (ExtendObjects.isEmpty(uuids)) {
                uuids = Lists.newArrayList();
            }
            uuids.add(client.getSessionId());
            sessionIdAndPrincipalId.put(client.getSessionId().toString(), principalId);
            principalSocketClient.put(principalId, uuids);
            WsConnectedEvent wsConnectedEvent = new WsConnectedEvent(principalId, deviceType);
            Object handledMessage = wsMessageHandle.handleMessage(principalId, connectedAction, JsonUtil.stringify(wsConnectedEvent));
            if (handledMessage != null) {
                client.sendEvent(connectedAction, handledMessage);
            }
            // 发布登录事件
            eventPublisher.publishEvent(wsConnectedEvent);
            log.info("WS:Netty-SocketIO:连接:{},{},{},目前有: {} 个连接", token, client.getSessionId(), deviceType, sessionIdAndPrincipalId.size());
        });

        server.addDisconnectListener(client -> {
            String token = getToken(client);
            String sessionId = client.getSessionId().toString();
            String principalId = sessionIdAndPrincipalId.get(sessionId);
            if (ExtendObjects.isBlank(principalId)) {
                log.error("WS:Netty-SocketIO:离线监听异常,找不到对应的 principalId");
                return;
            }
            List<UUID> sessionIds = principalSocketClient.get(principalId);
            sessionIdAndPrincipalId.remove(client.getSessionId().toString());
            if (ExtendObjects.isEmpty(sessionIds)) {
                log.error("WS:Netty-SocketIO:离线监听异常,找不到对应的 sessionId");
                return;
            }
            sessionIds.remove(client.getSessionId());
            DeviceType deviceType = getDeviceType(client);
            // 发布离线事件
            log.info("WS:Netty-SocketIO:断开连接:{},{},还有:{} 个连接", token, client.getSessionId(), sessionIdAndPrincipalId.size());
            eventPublisher.publishEvent(new WsDisconnectdEvent(principalId, deviceType));
        });

        wsMessageHandle.getAllAction().forEach(action -> {
            log.info("WS:Netty-SocketIO:启动监听事件:{}", action);
            server.addEventListener(action, wsMessageHandle.getByAction(action).getClazz(), (client, data, ackRequest) -> {
                String principalId = sessionIdAndPrincipalId.get(client.getSessionId().toString());
                if (principalId == null) {
                    log.error("未登录用户尝试发送消息,拒绝");
                    client.disconnect();
                    return;
                }
                Object handledMessage = wsMessageHandle.handleMessage(principalId, action, data);
                if (handledMessage != null) {
                    client.sendEvent(action, handledMessage);
                }
            });
        });
        server.start();
        log.info("Websocket-Netty-SocketIO:启动");
    }

    private String getToken(SocketIOClient client) {
        return client.getHandshakeData().getSingleUrlParam("token");
    }

    private DeviceType getDeviceType(SocketIOClient client) {
        String device = client.getHandshakeData().getSingleUrlParam("device");
        try {
            return DeviceType.valueOf(device);
        } catch (Exception e) {
            return DeviceType.UNKNOWN;
        }
    }

    @Override
    public void send(String principalId, String action, Object msg) {
        List<UUID> uuids = principalSocketClient.get(principalId);
        if (!ExtendObjects.isEmpty(uuids)) {
            uuids.forEach(uuid -> {
                SocketIOClient client = server.getClient(uuid);
                if (ExtendObjects.nonNull(client) && client.isChannelOpen()) {
                    client.sendEvent(action, msg);
                }
            });
        } else {
            log.info("Websocket-Netty-SocketIO:发送消息,对应的用户不在线:{}, {},{}", principalId, action, msg);
        }
    }

    @Override
    public void broadcast(String action, Object msg) {
        server.getBroadcastOperations().sendEvent(action, JsonUtil.stringify(msg));
    }

    @Override
    public void printOnline() {
        log.info("Websocket-Netty-SocketIO:打印连接数:内存记录设备在线:{},真正设备在线:{},用户数(扣除单用户多设备):{}", sessionIdAndPrincipalId.size(), server.getAllClients().size(), principalSocketClient.size());
    }

    @Override
    public boolean isOnline(String principalId) {
        return principalSocketClient.containsKey(principalId);
    }

    @Override
    public void destroy() throws Exception {
        log.info("Websocket-Netty-SocketIO:开始暂停");
        server.stop();
        server.getBroadcastOperations().getClients().forEach(ClientOperations::disconnect);
        Thread.sleep(10000);
        server.stop();
        log.info("Websocket-Netty-SocketIO:已停止");
    }
}
