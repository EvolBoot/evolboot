package org.evolboot.im.domain.chatrecord;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.chatrecord.repository.ChatRecordRepository;
import org.evolboot.im.domain.chatrecord.service.ChatRecordCreateFactory;
import org.evolboot.im.domain.chatrecord.service.ChatRecordSupportService;
import org.evolboot.im.domain.chatrecord.service.ChatRecordUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Slf4j
@Service
public class DefaultChatRecordAppService extends ChatRecordSupportService implements ChatRecordAppService {


    private final ChatRecordCreateFactory factory;
    private final ChatRecordUpdateService updateService;

    protected DefaultChatRecordAppService(ChatRecordRepository repository, ChatRecordCreateFactory factory, ChatRecordUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public ChatRecord create(ChatRecordCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, ChatRecordUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<ChatRecord> findAll() {
        return repository.findAll();
    }


    @Override
    public List<ChatRecord> findAll(ChatRecordQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<ChatRecord> page(ChatRecordQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<ChatRecord> findOne(ChatRecordQuery query) {
        return repository.findOne(query);
    }
}
