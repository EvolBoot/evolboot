package org.evolboot.im.domain.chatrecord;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.chatrecord.repository.ChatRecordRepository;
import org.evolboot.im.domain.chatrecord.service.ChatRecordSupportService;

import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.dto.ChatRecordQueryRequest;


import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 聊天记录
 *
 * @author evol
 * @date 2023-06-14 18:14:00
 */
@Slf4j
@Service
public class ChatRecordQueryServiceImpl  implements ChatRecordQueryService {

    private final ChatRecordRepository repository;
    private final ChatRecordSupportService supportService;

    protected ChatRecordQueryServiceImpl(ChatRecordRepository repository, ChatRecordSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public ChatRecord findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    public List<ChatRecord> findAll() {
        return repository.findAll();
    }


    @Override
    public List<ChatRecord> findAll(ChatRecordQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<ChatRecord> page(ChatRecordQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<ChatRecord> findOne(ChatRecordQueryRequest query) {
        return repository.findOne(query);
    }
}
