package org.evolboot.im.domain.conversation.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.im.domain.conversation.Conversation;

import java.util.List;
import java.util.Optional;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
public interface ConversationRepository extends BaseRepository<Conversation, Long> {

    Conversation save(Conversation conversation);

    Optional<Conversation> findById(Long id);

    Optional<Conversation> findByRelationId(String relationId);


    void deleteById(Long id);

    List<Conversation> findAll();

    <Q extends Query> List<Conversation> findAll(Q query);

    <Q extends Query> Optional<Conversation> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<Conversation> page(Q query);
}
