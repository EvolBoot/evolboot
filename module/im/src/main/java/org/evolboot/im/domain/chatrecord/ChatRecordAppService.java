package org.evolboot.im.domain.chatrecord;

import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.service.ChatRecordCreateFactory;
import org.evolboot.im.domain.chatrecord.service.ChatRecordUpdateService;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
public interface ChatRecordAppService {


    ChatRecord create(ChatRecordCreateFactory.Request request);

    void update(ChatRecordUpdateService.Request request);

    void delete(Long id);


}
