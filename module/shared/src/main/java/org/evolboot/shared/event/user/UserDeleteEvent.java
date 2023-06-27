package org.evolboot.shared.event.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.event.Event;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class UserDeleteEvent implements Event<Long> {

    private final Long userId;


    @Override
    public Long getEventSourceId() {
        return userId;
    }
}
