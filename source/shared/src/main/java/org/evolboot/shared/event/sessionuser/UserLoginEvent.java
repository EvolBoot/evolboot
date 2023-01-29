package org.evolboot.shared.event.sessionuser;

import org.evolboot.shared.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author evol
 * 
 */
@Getter
@AllArgsConstructor
public class UserLoginEvent implements Event<String> {

    private final Long userId;
    private final String loginToken;
    private final String loginIp;


    @Override
    public String getSource() {
        return loginToken;
    }

}
