package org.evolboot.im.domain.userconversation;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.userconversation.entity.UserConversation;
import org.evolboot.im.domain.userconversation.dto.UserConversationQuery;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 用户会话
 *
 * @author evol
 * @date 2023-06-14 20:07:28
 */
public interface UserConversationQueryService {

    UserConversation findById(Long id);

    List<UserConversation> findAll();

    List<UserConversation> findAll(UserConversationQuery query);

    Page<UserConversation> page(UserConversationQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<UserConversation> findOne(UserConversationQuery query);


}
