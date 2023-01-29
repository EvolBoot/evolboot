package org.evolboot.shared.event.permission;

import org.evolboot.shared.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author evol
 * 
 */
@Getter
@AllArgsConstructor
public class PermissionDeleteEvent implements Event<Long> {

    private final Long permissionId;

    @Override
    public Long getSource() {
        return permissionId;
    }
}
