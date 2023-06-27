package org.evolboot.shared.event.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.event.Event;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class RoleDeleteEvent implements Event<Long> {

    private final Long roleId;

    @Override
    public Long getEventSourceId() {
        return roleId;
    }
}

