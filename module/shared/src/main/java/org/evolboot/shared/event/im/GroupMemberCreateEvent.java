package org.evolboot.shared.event.im;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.event.Event;

import java.util.Date;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class GroupMemberCreateEvent implements Event<Long> {

    private Long id;

    /**
     * 群ID
     */
    private Long groupId;

    /**
     * 用户ID
     */
    private Long memberUserId;

    /**
     * 会话ID
     */
    private Long conversationId;



    @Override
    public Long getSource() {
        return id;
    }
}
