package org.evolboot.im.domain.conversation.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.conversation.entity.Conversation;
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
public class ConversationSupportService {

    protected final ConversationRepository repository;

    protected ConversationSupportService(ConversationRepository repository) {
        this.repository = repository;
    }

    public Conversation findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.Conversation.NOT_FOUND)));
    }

}
