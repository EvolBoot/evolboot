package org.evolboot.ws.nettysocketio.domain;

import com.corundumstudio.socketio.ClientOperations;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.event.ws.WsConnectedEvent;
import org.evolboot.shared.event.ws.WsDisconnectdEvent;
import org.evolboot.ws.core.WsExceptionMessage;
import org.evolboot.ws.core.WsMessageHandle;
import org.evolboot.ws.nettysocketio.autoconfigure.NettySocketIoConfiguration;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class NettyScoketIORunner implements DisposableBean {

    private final static String CONNECTED_ACTION = "connect";

    private final EventPublisher eventPublisher;

    private final WsMessageHandle wsMessageHandle;

    private SocketIOServer server;

    private final SessionClientManager sessionClientManager;

    private final NettySocketIoConfiguration nettySocketIoConfiguration;

    public NettyScoketIORunner(EventPublisher eventPublisher, WsMessageHandle wsMessageHandle, SessionClientManager sessionClientManager, NettySocketIoConfiguration nettySocketIoConfiguration) {
        this.eventPublisher = eventPublisher;
        this.wsMessageHandle = wsMessageHandle;
        this.sessionClientManager = sessionClientManager;
        this.nettySocketIoConfiguration = nettySocketIoConfiguration;
    }

    @Bean
    public SocketIOServer getSocketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(nettySocketIoConfiguration.getHost());
        config.setPort(nettySocketIoConfiguration.getPort());
        server = new SocketIOServer(config);

        // 连接
        server.addConnectListener(this::connectListener);

        //  离线
        server.addDisconnectListener(this::disconnectListener);

        // 处理其他消息
        wsMessageHandle.getAllAction().forEach(this::handleAction);

        // 启动
        server.start();

        log.info("WS:Netty-SocketIO:启动:端口:{}:{}", nettySocketIoConfiguration.getHost(), nettySocketIoConfiguration.getPort());

        return server;
    }


    /**
     * 连接监听
     *
     * @param client
     */
    private void connectListener(SocketIOClient client) {
        SessionClientManager.Device device = sessionClientManager.connect(client);
        if (device == null) {
            log.info("WS:Netty-SocketIO:连接:没有Token:断开连接:{}", client.isChannelOpen());
            client.disconnect();
            return;
        }
        WsConnectedEvent wsConnectedEvent = new WsConnectedEvent(device.getPrincipalId(), device.getDeviceType());
        Object handledMessage = wsMessageHandle.handleMessage(device.getPrincipalId(), CONNECTED_ACTION, JsonUtil.stringify(wsConnectedEvent));
        if (handledMessage != null) {
            if (handledMessage instanceof WsExceptionMessage) {
                client.sendEvent(WsExceptionMessage.ERROR_ACTION, handledMessage);
            } else {
                client.sendEvent(CONNECTED_ACTION, handledMessage);
            }
        }
        // 发布登录事件
        eventPublisher.publishEvent(wsConnectedEvent);
        log.info("WS:Netty-SocketIO:连接:{},{},{},目前有: {} 个连接", device.getToken(), client.getSessionId(), device.getDeviceType(), sessionClientManager.getOnline());
    }

    /**
     * 离线监听
     *
     * @param client
     */
    private void disconnectListener(SocketIOClient client) {
        SessionClientManager.Device device = sessionClientManager.disconnect(client);
        if (device == null) {
            log.error("WS:Netty-SocketIO:离线监听异常,找不到对应的 principalId");
            client.disconnect();
            return;
        }
        // 发布离线事件
        log.info("WS:Netty-SocketIO:断开连接:{},{},还有:{} 个连接", device.getToken(), client.getSessionId(), sessionClientManager.getOnline());
        eventPublisher.publishEvent(new WsDisconnectdEvent(device.getPrincipalId(), device.getDeviceType()));
    }

    /**
     * 处理其他消息
     *
     * @param action 动作
     */
    private void handleAction(String action) {
        log.info("WS:Netty-SocketIO:启动监听事件:{}", action);
        server.addEventListener(action, wsMessageHandle.getByAction(action).getClazz(), (client, data, ackRequest) -> {
            SessionClientManager.Device device = sessionClientManager.findBySocketClient(client);
            if (device == null) {
                log.error("WS:Netty-SocketIO:未登录用户尝试发送消息,拒绝");
                client.disconnect();
                return;
            }
            Object handledMessage = wsMessageHandle.handleMessage(device.getPrincipalId(), action, data);
            if (handledMessage != null) {
                if (handledMessage instanceof WsExceptionMessage) {
                    client.sendEvent(WsExceptionMessage.ERROR_ACTION, handledMessage);
                } else {
                    client.sendEvent(action, handledMessage);
                }
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        log.info("WS:Netty-SocketIO:开始暂停");
        server.stop();
        server.getBroadcastOperations().getClients().forEach(ClientOperations::disconnect);
        Thread.sleep(10000);
        server.stop();
        log.info("WS:Netty-SocketIO:已停止");
    }
}