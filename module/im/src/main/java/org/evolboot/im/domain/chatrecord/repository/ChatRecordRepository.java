package org.evolboot.im.domain.chatrecord.repository;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.im.domain.chatrecord.ChatRecord;
import org.evolboot.im.domain.chatrecord.ChatRecord;
import org.evolboot.im.domain.chatrecord.ChatRecordQuery;


import java.util.List;
import java.util.Optional;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
public interface ChatRecordRepository {

    ChatRecord save(ChatRecord chatRecord);

    Optional<ChatRecord> findById(Long id);

    Page<ChatRecord> page(ChatRecordQuery query);

    void deleteById(Long id);

    List<ChatRecord> findAll();

    List<ChatRecord> findAll(ChatRecordQuery query);
}
