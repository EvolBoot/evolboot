package org.evolboot.im.domain.userconversation;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.userconversation.service.UserConversationCreateFactory;
import org.evolboot.im.domain.userconversation.service.UserConversationUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
public interface UserConversationAppService {

    UserConversation findById(Long id);

    /**
     * 创建用户会话
     */

    UserConversation create(UserConversationCreateFactory.Request request);

    void update(Long id, UserConversationUpdateService.Request request);

    void delete(Long id);

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
