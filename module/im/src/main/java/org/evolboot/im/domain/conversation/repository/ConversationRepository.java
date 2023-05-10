package org.evolboot.im.domain.conversation.repository;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.im.domain.conversation.Conversation;
import org.evolboot.im.domain.conversation.Conversation;
import org.evolboot.im.domain.conversation.ConversationQuery;


import java.util.List;
import java.util.Optional;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
public interface ConversationRepository {

    Conversation save(Conversation conversation);

    Optional<Conversation> findById(Long id);

    Optional<Conversation> findByRelationId(String relationId);

    Page<Conversation> page(ConversationQuery query);

    void deleteById(Long id);

    List<Conversation> findAll();

    List<Conversation> findAll(ConversationQuery query);

    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<Conversation> findOne(ConversationQuery query);

}
