package org.evolboot.im.domain.conversation;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.service.ConversationCreateFactory;
import org.evolboot.im.domain.conversation.service.ConversationQuery;
import org.evolboot.im.domain.conversation.service.ConversationUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
public interface ConversationAppService {


    Conversation create(ConversationCreateFactory.Request request);

    Conversation addPeople(Long id);

    Conversation reductionPeople(Long id);

    void update(ConversationUpdateService.Request request);

    void delete(Long id);


}
