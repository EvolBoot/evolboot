package org.evolboot.im.domain.conversation.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.repository.ConversationRepository;
import org.evolboot.im.domain.shared.ConversationType;
import org.springframework.stereotype.Service;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Slf4j
@Service
public class ConversationCreateFactory extends ConversationSupportService {
    protected ConversationCreateFactory(ConversationRepository repository) {
        super(repository);
    }

    public Conversation execute(Request request) {
        Conversation conversation = repository.findByRelationId(request.getRelationId()).orElseGet(() -> new Conversation(
                request.getType(),
                request.getRelationId()
        ));
        repository.save(conversation);
        return conversation;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    @Setter
    public static class Request {
        /**
         * 会话类型
         */
        private ConversationType type;

        /**
         * 关联的ID
         */
        private String relationId;

    }

}
