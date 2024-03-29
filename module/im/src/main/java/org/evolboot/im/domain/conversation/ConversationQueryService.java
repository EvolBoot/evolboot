package org.evolboot.im.domain.conversation;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.dto.ConversationQueryRequest;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 会话
 *
 * @author evol
 * @date 2023-06-14 18:16:10
 */
public interface ConversationQueryService {

    Conversation findById(Long id);

    List<Conversation> findAll();

    List<Conversation> findAll(ConversationQueryRequest query);

    Page<Conversation> page(ConversationQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Conversation> findOne(ConversationQueryRequest query);


}
