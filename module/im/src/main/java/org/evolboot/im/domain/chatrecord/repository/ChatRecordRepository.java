package org.evolboot.im.domain.chatrecord.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.im.domain.chatrecord.ChatRecord;

import java.util.List;
import java.util.Optional;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
public interface ChatRecordRepository extends BaseRepository<ChatRecord, Long> {

    ChatRecord save(ChatRecord chatRecord);

    Optional<ChatRecord> findById(Long id);

    void deleteById(Long id);

    List<ChatRecord> findAll();

    <Q extends Query> List<ChatRecord> findAll(Q query);

    <Q extends Query> Optional<ChatRecord> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<ChatRecord> page(Q query);

}
