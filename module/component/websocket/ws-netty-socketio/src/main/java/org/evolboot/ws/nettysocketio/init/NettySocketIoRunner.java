package org.evolboot.ws.nettysocketio.init;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.ws.core.WsMessageData;
import org.evolboot.ws.core.WsMessageHandle;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.listener.*;
import com.corundumstudio.socketio.*;

/**
 * @author evol
 */
@Service
@Slf4j
public class NettySocketIoRunner implements CommandLineRunner, DisposableBean {

    private final WsMessageHandle wsMessageHandle;

    SocketIOServer server;
    SocketIONamespace chat1namespace;

    public NettySocketIoRunner(WsMessageHandle wsMessageHandle) {
        this.wsMessageHandle = wsMessageHandle;
    }

    @Override
    public void run(String... args) throws Exception {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);
        server = new SocketIOServer(config);
        chat1namespace = server.addNamespace("/message");
        chat1namespace.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {

            }
        });
        chat1namespace.addEventListener("message", WsMessageData.class, new DataListener<WsMessageData>() {
            @Override
            public void onData(SocketIOClient client, WsMessageData data, AckRequest ackRequest) {
                System.out.println(JsonUtil.stringify(data));
                chat1namespace.getBroadcastOperations().sendEvent("message", "hehehehhe");
                wsMessageHandle.handleMessage(data.getAction(), data.getContent());
            }
        });
        server.start();
        log.info("Websocket-Netty-SocketIO:启动");
    }

    @Override
    public void destroy() throws Exception {
        log.info("Websocket-Netty-SocketIO:停止");
        server.stop();
    }
}
