package org.evolboot.im.domain.chatrecord;

import org.evolboot.core.data.Page;
import org.springframework.transaction.annotation.Transactional;
import org.evolboot.im.domain.chatrecord.service.ChatRecordCreateFactory;
import org.evolboot.im.domain.chatrecord.service.ChatRecordUpdateService;

import java.util.List;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
public interface ChatRecordAppService {

    ChatRecord findById(Long id);

    ChatRecord create(ChatRecordCreateFactory.Request request);

    void update(Long id, ChatRecordUpdateService.Request request);

    void delete(Long id);

    List<ChatRecord> findAll();

    List<ChatRecord> findAll(ChatRecordQuery query);

    Page<ChatRecord> page(ChatRecordQuery query);


}
