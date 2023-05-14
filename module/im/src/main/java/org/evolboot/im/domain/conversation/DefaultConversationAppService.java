package org.evolboot.im.domain.conversation;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.im.ImAccessAuthorities;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.conversation.repository.ConversationRepository;
import org.evolboot.im.domain.conversation.service.ConversationCreateFactory;
import org.evolboot.im.domain.conversation.service.ConversationSupportService;
import org.evolboot.im.domain.conversation.service.ConversationUpdateService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

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
public class DefaultConversationAppService extends ConversationSupportService implements ConversationAppService {


    private final ConversationCreateFactory factory;
    private final ConversationUpdateService updateService;

    protected DefaultConversationAppService(ConversationRepository repository, ConversationCreateFactory factory, ConversationUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
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
    public void update(Long id, ConversationUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<Conversation> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Conversation> findAll(ConversationQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Conversation> page(ConversationQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<Conversation> findOne(ConversationQuery query) {
        return repository.findOne(query);
    }
}
