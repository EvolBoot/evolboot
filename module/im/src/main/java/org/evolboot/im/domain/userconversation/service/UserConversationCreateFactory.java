package org.evolboot.im.domain.userconversation.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.conversation.ConversationAppService;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.im.domain.userconversation.UserConversation;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;
import org.springframework.stereotype.Service;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Slf4j
@Service
public class UserConversationCreateFactory extends UserConversationSupportService {

    private final ConversationAppService conversationAppService;

    protected UserConversationCreateFactory(UserConversationRepository repository, ConversationAppService conversationAppService) {
        super(repository);
        this.conversationAppService = conversationAppService;
    }

    public UserConversation execute(Request request) {


        UserConversation userConversation = repository.findByOwnerUserIdAndConversationId(request.getOwnerUserId(), request.getConversationId())
                .orElseGet(() -> {
                    log.info("增加用户会话:{},{},{}", request.getOwnerUserId(), request.getConversationId(), request.getConversationType());
                    conversationAppService.addPeople(request.getConversationId());
                    return new UserConversation(
                            request.getOwnerUserId(),
                            request.getConversationId(),
                            request.getConversationType(),
                            request.getGroupId(),
                            request.getFriendUserId()
                    );
                });
        repository.save(userConversation);
        return userConversation;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private Long ownerUserId;

        /**
         * 会话ID
         */
        private Long conversationId;

        /**
         * 会话类型
         */
        private ConversationType conversationType;

        /**
         * 群ID，如果会话类型为 GROUP，则此ID不为空
         */
        private Long groupId;

        /**
         * 朋友会话ID，如果类型为 SINGLE ，则此ID不为空
         */
        private Long friendUserId;

    }

}
