package org.evolboot.shared.event.im;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.event.Event;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class FriendRemoveBlacklistEvent implements Event<Long> {

    private Long id;

    private Long ownerUserId;

    private Long friendUserId;

    private Long conversationId;

    @Override
    public Long getEventSourceId() {
        return id;
    }
}
