package org.evolboot.shared.event.ws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.evolboot.shared.event.Event;
import org.evolboot.shared.lang.DeviceType;

/**
 * @author evol
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WsConnectedEvent implements Event<String> {

    private String principalId;
    private DeviceType deviceType;

    @Override
    public String getEventSourceId() {
        return principalId;
    }
}