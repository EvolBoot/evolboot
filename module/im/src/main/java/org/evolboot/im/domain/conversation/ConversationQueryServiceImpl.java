package org.evolboot.im.domain.conversation;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.conversation.repository.ConversationRepository;
import org.evolboot.im.domain.conversation.service.ConversationSupportService;

import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.dto.ConversationQueryRequest;


import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 会话
 *
 * @author evol
 * @date 2023-06-14 18:16:10
 */
@Slf4j
@Service
public class ConversationQueryServiceImpl  implements ConversationQueryService {

    private final ConversationRepository repository;
    private final ConversationSupportService supportService;

    protected ConversationQueryServiceImpl(ConversationRepository repository, ConversationSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public Conversation findById(Long id) {
        return supportService.findById(id);
    }
    @Override
    public List<Conversation> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Conversation> findAll(ConversationQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Conversation> page(ConversationQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<Conversation> findOne(ConversationQueryRequest query) {
        return repository.findOne(query);
    }
}
