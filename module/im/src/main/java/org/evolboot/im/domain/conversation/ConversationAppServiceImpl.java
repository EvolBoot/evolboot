package org.evolboot.im.domain.conversation;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.repository.ConversationRepository;
import org.evolboot.im.domain.conversation.service.ConversationCreateFactory;
import org.evolboot.im.domain.conversation.service.ConversationQuery;
import org.evolboot.im.domain.conversation.service.ConversationSupportService;
import org.evolboot.im.domain.conversation.service.ConversationUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Slf4j
@Service
public class ConversationAppServiceImpl  implements ConversationAppService {


    private final ConversationCreateFactory factory;
    private final ConversationUpdateService updateService;

    private final ConversationRepository repository;

    private final ConversationSupportService supportService;

    protected ConversationAppServiceImpl(ConversationRepository repository, ConversationCreateFactory factory, ConversationUpdateService updateService, ConversationSupportService supportService) {
        this.factory = factory;
        this.updateService = updateService;
        this.repository = repository;
        this.supportService = supportService;
    }

    public Conversation findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    @Transactional
    public Conversation create(ConversationCreateFactory.Request request) {
        return factory.execute(request);
    }

    @Override
    @Transactional
    public Conversation addPeople(Long id) {
        Conversation conversation = findById(id).addPeople();
        repository.save(conversation);
        return conversation;
    }

    @Override
    @Transactional
    public Conversation reductionPeople(Long id) {
        Conversation conversation = findById(id).reductionPeople();
        repository.save(conversation);
        return conversation;
    }


    @Override
    @Transactional
    public void update(ConversationUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

}
