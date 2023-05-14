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
public class UserConversationDeleteService extends UserConversationSupportService {

    private final ConversationAppService conversationAppService;

    protected UserConversationDeleteService(UserConversationRepository repository, ConversationAppService conversationAppService) {
        super(repository);
        this.conversationAppService = conversationAppService;
    }

    public void execute(Long ownerUserId, Long conversationId) {
        repository.findByOwnerUserIdAndConversationId(ownerUserId, conversationId).ifPresent(userConversation -> {
            log.info("删除会话:{},{},{}", userConversation.getOwnerUserId(), userConversation.getConversationId(), userConversation.getConversationType());
            conversationAppService.reductionPeople(conversationId);
            repository.deleteById(userConversation.id());
        });
    }

}
