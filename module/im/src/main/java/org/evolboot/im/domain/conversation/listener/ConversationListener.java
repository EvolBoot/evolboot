package org.evolboot.im.domain.conversation.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.conversation.repository.ConversationRepository;
import org.evolboot.im.domain.conversation.service.ConversationSupportService;
import org.springframework.stereotype.Service;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Service
@Slf4j
public class ConversationListener extends ConversationSupportService {

    protected ConversationListener(ConversationRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
