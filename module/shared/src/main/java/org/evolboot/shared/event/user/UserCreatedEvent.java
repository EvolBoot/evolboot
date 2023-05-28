package org.evolboot.shared.event.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.event.Event;
import org.evolboot.shared.lang.UserIdentity;

import java.util.Set;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class UserCreatedEvent implements Event<Long> {

    private final Long userId;
    private final Set<UserIdentity> currentUserIdentity;

    @Override
    public Long getSource() {
        return userId;
    }
}
