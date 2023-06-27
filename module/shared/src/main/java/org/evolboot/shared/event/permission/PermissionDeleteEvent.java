package org.evolboot.shared.event.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.event.Event;

import java.util.List;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class PermissionDeleteEvent implements Event<List<Long>> {

    private final List<Long> permissionIds;

    @Override
    public List<Long> getEventSourceId() {
        return permissionIds;
    }
}
