package org.evolboot.im.domain.chatrecord;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.dto.ChatRecordQueryRequest;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 聊天记录
 *
 * @author evol
 * @date 2023-06-14 18:14:00
 */
public interface ChatRecordQueryService {

    ChatRecord findById(Long id);

    List<ChatRecord> findAll();

    List<ChatRecord> findAll(ChatRecordQueryRequest query);

    Page<ChatRecord> page(ChatRecordQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<ChatRecord> findOne(ChatRecordQueryRequest query);


}
