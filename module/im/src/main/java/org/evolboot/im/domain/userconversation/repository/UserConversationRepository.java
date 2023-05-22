package org.evolboot.im.domain.userconversation.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.im.domain.userconversation.entity.UserConversation;

import java.util.List;
import java.util.Optional;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
public interface UserConversationRepository extends BaseRepository<UserConversation, Long> {

    UserConversation save(UserConversation userConversation);

    Optional<UserConversation> findById(Long id);


    void deleteById(Long id);

    List<UserConversation> findAll();


    /**
     * 查找用户的会话
     *
     * @param ownerUserId
     * @param conversationId
     * @return
     */
    Optional<UserConversation> findByOwnerUserIdAndConversationId(Long ownerUserId, Long conversationId);


    <Q extends Query> List<UserConversation> findAll(Q query);

    <Q extends Query> Optional<UserConversation> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<UserConversation> page(Q query);

}
