package org.evolboot.im.domain.chatrecord.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.repository.ChatRecordRepository;
import org.springframework.stereotype.Service;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Slf4j
@Service
public class ChatRecordUpdateService extends ChatRecordSupportService {
    protected ChatRecordUpdateService(ChatRecordRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        ChatRecord chatRecord = findById(id);
        repository.save(chatRecord);
    }

    public static class Request extends ChatRecordRequestBase {
    }

}
