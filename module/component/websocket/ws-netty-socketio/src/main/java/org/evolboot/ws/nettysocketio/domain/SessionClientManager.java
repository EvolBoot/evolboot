package org.evolboot.ws.nettysocketio.domain;

import com.corundumstudio.socketio.SocketIOClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.lang.DeviceType;
import org.evolboot.ws.core.authentication.WsAuthenticationServiceManager;
import org.evolboot.ws.core.authentication.WsAuthenticationType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author evol
 */
@Component
@Slf4j
public final class SessionClientManager {

    /**
     * 全部设备
     */
    private final Map<String, List<Device>> devices = Maps.newConcurrentMap();
    /**
     * 从sessionId 找 Device
     */
    private final Map<String, Device> sessionIdAndDevice = Maps.newConcurrentMap();

    private final static String TOKEN_KEY = "token";
    private final static String DEVICE_KEY = "device";


    /**
     * 上线
     *
     * @param client
     * @return
     */
    public Device connect(SocketIOClient client) {
        String token = getToken(client);
        DeviceType deviceType = getDeviceType(client);
        log.info("WS:Netty-SocketIO:连接:{},{},{}", token, client.getSessionId(), deviceType);
        if (ExtendObjects.isBlank(token)) {
            log.info("WS:Netty-SocketIO:连接:没有Token:断开连接:{}", client.isChannelOpen());
            return null;
        }
        String principalId = WsAuthenticationServiceManager.getWsAuthenticationService(WsAuthenticationType.TOKEN).findPrincipalIdByToken(token);
        List<Device> devices = this.devices.get(principalId);
        if (ExtendObjects.isEmpty(devices)) {
            devices = Lists.newArrayList();
        }
        Device device = new Device(
                client.getSessionId(),
                principalId,
                getDeviceType(client),
                token
        );
        devices.add(device);
        this.sessionIdAndDevice.put(client.getSessionId().toString(), device);
        this.devices.put(principalId, devices);
        return device;
    }

    /**
     * 离线
     *
     * @param client
     * @return
     */
    public Device disconnect(SocketIOClient client) {
        Device device = findBySocketClient(client);
        if (device != null) {
            List<Device> deviceList = findByPrincipalId(device.getPrincipalId());
            if (deviceList != null) {
                findByPrincipalId(device.getPrincipalId()).remove(device);
            }
        }
        sessionIdAndDevice.remove(client.getSessionId().toString());
        return device;
    }

    /**
     * 是否在线
     *
     * @param principalId
     * @return
     */
    public boolean isOnline(String principalId) {
        return devices.containsKey(principalId);
    }


    public Device findBySocketClient(SocketIOClient client) {
        return sessionIdAndDevice.get(client.getSessionId().toString());
    }

    public List<Device> findByPrincipalId(String principalId) {
        return devices.get(principalId);
    }

    public int getOnline() {
        return sessionIdAndDevice.size();
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Device {
        private UUID sessionId;
        private String principalId;
        private DeviceType deviceType;
        private String token;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Device device = (Device) o;

            return sessionId.equals(device.sessionId);
        }

        @Override
        public int hashCode() {
            return sessionId.hashCode();
        }
    }

    public String getToken(SocketIOClient client) {
        return client.getHandshakeData().getSingleUrlParam(TOKEN_KEY);
    }


    public DeviceType getDeviceType(SocketIOClient client) {
        String device = client.getHandshakeData().getSingleUrlParam(DEVICE_KEY);
        try {
            return DeviceType.valueOf(device);
        } catch (Exception e) {
            return DeviceType.UNKNOWN;
        }
    }

}
