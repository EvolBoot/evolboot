package org.evolboot.im.domain.chatrecord;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.chatrecord.repository.ChatRecordRepository;
import org.evolboot.im.domain.chatrecord.service.ChatRecordSupportService;
import org.springframework.stereotype.Service;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Service
@Slf4j
public class ChatRecordListener extends ChatRecordSupportService {

    protected ChatRecordListener(ChatRecordRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
