package org.evolboot.identity.domain.user.repository;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.UserQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByMobile(String mobile);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrMobileOrEmail(String value);

    Optional<User> findById(Long userId);

    Page<User> page(UserQuery userQuery);

    boolean existsByMobile(String mobile);

    List<UserIdAndInviterUserId> findIdAndInviterUserId();


    /**
     * 根据用户ID显示头像
     *
     * @param userId
     * @return
     */
    String findAvatarByUserId(Long userId);
}
