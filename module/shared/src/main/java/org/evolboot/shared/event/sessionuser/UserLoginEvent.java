package org.evolboot.shared.event.sessionuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.event.Event;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class UserLoginEvent implements Event<String> {

    private final Long userId;
    private final String loginToken;
    private final String loginIp;


    @Override
    public String getEventSourceId() {
        return loginToken;
    }

}
