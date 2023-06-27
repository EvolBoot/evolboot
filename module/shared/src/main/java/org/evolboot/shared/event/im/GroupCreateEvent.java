package org.evolboot.shared.event.im;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.event.Event;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class GroupCreateEvent implements Event<Long> {

    private Long groupId;

    /**
     * 群主
     */
    private Long ownerUserId;

    /**
     * 群名称
     */
    private String name;


    /**
     * 会话ID
     */
    private Long conversationId;


    @Override
    public Long getEventSourceId() {
        return groupId;
    }
}
