package org.evolboot.im.domain.userconversation.repository;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.im.domain.userconversation.UserConversation;
import org.evolboot.im.domain.userconversation.UserConversation;
import org.evolboot.im.domain.userconversation.UserConversationQuery;


import java.util.List;
import java.util.Optional;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
public interface UserConversationRepository {

    UserConversation save(UserConversation userConversation);

    Optional<UserConversation> findById(Long id);

    Page<UserConversation> page(UserConversationQuery query);

    void deleteById(Long id);

    List<UserConversation> findAll();

    List<UserConversation> findAll(UserConversationQuery query);
}
