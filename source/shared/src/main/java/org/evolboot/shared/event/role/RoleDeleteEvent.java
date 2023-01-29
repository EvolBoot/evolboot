package org.evolboot.shared.event.role;

import org.evolboot.shared.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author evol
 * 
 */
@Getter
@AllArgsConstructor
public class RoleDeleteEvent implements Event<Long> {

    private final Long roleId;

    @Override
    public Long getSource() {
        return roleId;
    }
}

