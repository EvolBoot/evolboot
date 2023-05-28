package org.evolboot.im.domain.chatrecord.service;

import lombok.Getter;
import lombok.Setter;
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

    public void execute(Request request) {
        ChatRecord chatRecord = findById(request.getId());
        repository.save(chatRecord);
    }

    @Getter
    @Setter
    public static class Request extends ChatRecordRequestBase {
        private Long id;
    }

}
