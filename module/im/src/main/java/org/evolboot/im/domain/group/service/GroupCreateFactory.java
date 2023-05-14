package org.evolboot.im.domain.group.service;


import lombok.Getter;
import lombok.Setter;
import org.evolboot.im.domain.conversation.Conversation;
import org.evolboot.im.domain.conversation.ConversationAppService;
import org.evolboot.im.domain.conversation.service.ConversationCreateFactory;
import org.evolboot.im.domain.shared.ConversationType;
import org.springframework.stereotype.Service;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.evolboot.im.domain.group.Group;
import lombok.extern.slf4j.Slf4j;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Slf4j
@Service
public class GroupCreateFactory extends GroupSupportService {

    private final ConversationAppService conversationAppService;

    protected GroupCreateFactory(GroupRepository repository, ConversationAppService conversationAppService) {
        super(repository);
        this.conversationAppService = conversationAppService;
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
        return group;
    }

    @Getter
    @Setter
    public static class Request {

        private Long ownerUserId;

        private String name;

        private String avatar;

        private String description;
    }

}
