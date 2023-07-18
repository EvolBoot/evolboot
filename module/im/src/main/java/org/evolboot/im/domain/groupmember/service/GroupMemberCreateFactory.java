package org.evolboot.im.domain.groupmember.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.im.domain.groupmember.entity.GroupMember;
import org.evolboot.im.domain.groupmember.entity.GroupMemberRole;
import org.evolboot.im.domain.groupmember.entity.GroupMemberState;
import org.evolboot.im.domain.groupmember.repository.GroupMemberRepository;
import org.evolboot.shared.event.im.GroupMemberCreateEvent;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Slf4j
@Service
public class GroupMemberCreateFactory extends GroupMemberSupportService {

    private final EventPublisher eventPublisher;

    protected GroupMemberCreateFactory(GroupMemberRepository repository, EventPublisher eventPublisher) {
        super(repository);
        this.eventPublisher = eventPublisher;
    }

    public GroupMember execute(Request request) {
        GroupMember groupMember = new GroupMember(
                request.getGroupId(),
                request.getMemberUserId(),
                request.getConversationId(),
                request.getRole(),
                request.getState(),
                request.getForbidTalkDeadline()
        );
        eventPublisher.publishEvent(
                new GroupMemberCreateEvent(
                        groupMember.id(),
                        groupMember.getGroupId(),
                        groupMember.getMemberUserId(),
                        groupMember.getConversationId()
                ));
        repository.save(groupMember);
        return groupMember;
    }

    @Getter
    @AllArgsConstructor
    public static class Request {
        private Long groupId;
        private Long memberUserId;
        private Long conversationId;
        private GroupMemberRole role;
        private GroupMemberState state;
        private Date forbidTalkDeadline;
    }

}
