package org.evolboot.identity.domain.user.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.identity.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface UserRepository extends BaseRepository<User, Long> {

    User save(User user);

    Optional<User> findById(Long aLong);

    Optional<User> findByUsername(String username);

    Optional<User> findByMobile(String mobile);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrMobileOrEmail(String value);


    boolean existsByMobile(String mobile);

    List<UserIdAndInviterUserId> findIdAndInviterUserId();

    /**
     * 根据用户ID显示头像
     *
     * @param userId
     * @return
     */
    String findAvatarByUserId(Long userId);

    List<User> findAllById(Iterable<Long> ids);

    <Q extends Query> List<User> findAll(Q query);

    <Q extends Query> Optional<User> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<User> page(Q query);
}
