package org.evolboot.im.domain.chatrecord;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.repository.ChatRecordRepository;
import org.evolboot.im.domain.chatrecord.service.ChatRecordCreateFactory;
import org.evolboot.im.domain.chatrecord.service.ChatRecordSupportService;
import org.evolboot.im.domain.chatrecord.service.ChatRecordUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Slf4j
@Service
public class ChatRecordAppServiceImpl implements ChatRecordAppService {


    private final ChatRecordCreateFactory factory;
    private final ChatRecordUpdateService updateService;

    private final ChatRecordRepository repository;

    private final ChatRecordSupportService supportService;

    protected ChatRecordAppServiceImpl(ChatRecordRepository repository, ChatRecordCreateFactory factory, ChatRecordUpdateService updateService, ChatRecordSupportService supportService) {
        this.factory = factory;
        this.updateService = updateService;
        this.repository = repository;
        this.supportService = supportService;
    }


    @Override
    @Transactional
    public ChatRecord create(ChatRecordCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(ChatRecordUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        supportService.findById(id);
        repository.deleteById(id);
    }

}
