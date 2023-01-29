package org.evolboot.shared.event.user;

import org.evolboot.shared.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class UserDeleteEvent implements Event<Long> {

    private final Long userId;


    @Override
    public Long getSource() {
        return userId;
    }
}
