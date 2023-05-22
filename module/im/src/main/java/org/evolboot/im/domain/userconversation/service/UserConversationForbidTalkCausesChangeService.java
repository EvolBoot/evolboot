package org.evolboot.im.domain.userconversation.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.userconversation.entity.UserConversationForbidTalkCause;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserConversationForbidTalkCausesChangeService extends UserConversationSupportService {
    protected UserConversationForbidTalkCausesChangeService(UserConversationRepository repository) {
        super(repository);
    }

    /**
     * 增加禁言原因，并切会话状态改为禁言
     */
    public void addForbidTalkCauses(Long ownerUserId, Long conversationId, UserConversationForbidTalkCause forbidTalkCause) {
        repository.findByOwnerUserIdAndConversationId(ownerUserId, conversationId).ifPresent(userConversation -> {
            userConversation.addForbidTalkCauses(forbidTalkCause);
            repository.save(userConversation);
        });
    }

    /**
     * 移除禁言原因，如果禁言原因为空，则恢复正常聊天
     */
    public void removeForbidTalkCauses(Long ownerUserId, Long conversationId, UserConversationForbidTalkCause forbidTalkCause) {
        repository.findByOwnerUserIdAndConversationId(ownerUserId, conversationId).ifPresent(userConversation -> {
            userConversation.removeForbidTalkCauses(forbidTalkCause);
            repository.save(userConversation);
        });

    }


}
