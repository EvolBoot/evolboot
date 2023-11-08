package org.evolboot.im.domain.group.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.ConversationAppService;
import org.evolboot.im.domain.conversation.service.ConversationCreateFactory;
import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.evolboot.im.domain.groupmember.GroupMemberAppService;
import org.evolboot.im.domain.groupmember.entity.GroupMemberRole;
import org.evolboot.im.domain.groupmember.entity.GroupMemberState;
import org.evolboot.im.domain.groupmember.service.GroupMemberCreateFactory;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.shared.event.im.GroupCreateEvent;
import org.springframework.stereotype.Service;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Slf4j
@Service
public class GroupCreateFactory {

    private final GroupSupportService supportService;

    private final GroupRepository repository;

    private final ConversationAppService conversationAppService;

    private final EventPublisher eventPublisher;

    private final GroupMemberAppService groupMemberAppService;


    protected GroupCreateFactory(GroupRepository repository, ConversationAppService conversationAppService, EventPublisher eventPublisher, GroupMemberAppService groupMemberAppService, GroupSupportService supportService) {
        this.supportService = supportService;
        this.repository = repository;
        this.conversationAppService = conversationAppService;
        this.eventPublisher = eventPublisher;
        this.groupMemberAppService = groupMemberAppService;
    }

    public Group execute(Request request) {
        Long groupId = Group.generateId();
        Conversation conversation = conversationAppService.create(new ConversationCreateFactory.Request(ConversationType.GROUP, groupId.toString()));
        Group group = new Group(
                groupId,
                request.getOwnerUserId(),
                request.getName(),
                request.getAvatar(),
                request.getDescription(),
                conversation.id()
        );
        repository.save(group);
        groupMemberAppService.create(new GroupMemberCreateFactory.Request(
                groupId,
                group.getOwnerUserId(),
                group.getConversationId(),
                GroupMemberRole.OWNER,
                GroupMemberState.NORMAL,
                null
        ));
        eventPublisher.publishEvent(new GroupCreateEvent(groupId, group.getOwnerUserId(), group.getName(), group.getConversationId()));
        return group;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private Long ownerUserId;

        private String name;

        private String avatar;

        private String description;
    }

}
