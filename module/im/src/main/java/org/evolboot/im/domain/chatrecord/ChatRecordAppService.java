package org.evolboot.im.domain.chatrecord;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.service.ChatRecordCreateFactory;
import org.evolboot.im.domain.chatrecord.service.ChatRecordQuery;
import org.evolboot.im.domain.chatrecord.service.ChatRecordUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
public interface ChatRecordAppService {

    ChatRecord findById(Long id);

    ChatRecord create(ChatRecordCreateFactory.Request request);

    void update(ChatRecordUpdateService.Request request);

    void delete(Long id);

    List<ChatRecord> findAll();

    List<ChatRecord> findAll(ChatRecordQuery query);

    Page<ChatRecord> page(ChatRecordQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<ChatRecord> findOne(ChatRecordQuery query);


}
