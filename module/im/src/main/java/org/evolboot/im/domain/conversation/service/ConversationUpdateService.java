package org.evolboot.im.domain.conversation.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.conversation.Conversation;
import org.evolboot.im.domain.conversation.ConversationRequestBase;
import org.evolboot.im.domain.conversation.repository.ConversationRepository;
import org.springframework.stereotype.Service;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Slf4j
@Service
public class ConversationUpdateService extends ConversationSupportService {
    protected ConversationUpdateService(ConversationRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        Conversation conversation = findById(id);
        repository.save(conversation);
    }

    public static class Request extends ConversationRequestBase {
    }

}
