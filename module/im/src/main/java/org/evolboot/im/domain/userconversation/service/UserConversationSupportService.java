package org.evolboot.im.domain.userconversation.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.userconversation.UserConversation;
import org.evolboot.im.domain.userconversation.repository.UserConversationRepository;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Slf4j
public abstract class UserConversationSupportService {

    protected final UserConversationRepository repository;

    protected UserConversationSupportService(UserConversationRepository repository) {
        this.repository = repository;
    }

    public UserConversation findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.UserConversation.NOT_FOUND)));
    }

}
