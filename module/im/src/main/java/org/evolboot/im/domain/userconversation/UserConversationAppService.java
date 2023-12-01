package org.evolboot.im.domain.userconversation;

import org.evolboot.im.domain.userconversation.entity.UserConversation;
import org.evolboot.im.domain.userconversation.service.UserConversationCreateFactory;
import org.evolboot.im.domain.userconversation.service.UserConversationUpdateService;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
public interface UserConversationAppService {


    /**
     * 创建用户会话
     */

    UserConversation create(UserConversationCreateFactory.Request request);

    void update(UserConversationUpdateService.Request request);

    void delete(Long id);


}
