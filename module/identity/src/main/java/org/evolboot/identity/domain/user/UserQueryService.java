package org.evolboot.identity.domain.user;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.dto.UserQueryRequest;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 用户
 *
 * @author evol
 * @date 2023-06-14 15:03:11
 */
public interface UserQueryService {

    User findById(Long id);

    List<User> findAll();

    List<User> findAll(UserQueryRequest query);

    Page<User> page(UserQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<User> findOne(UserQueryRequest query);


    /**
     * 查看手机号是否存在
     *
     * @param mobile
     * @return
     */
    boolean existsByMobile(String mobile);


    /**
     * 查找用户名或者手机号或者邮箱和加密的密码
     *
     * @param account
     * @param encodePassword
     * @return
     */
    User findByUsernameOrMobileOrEmailAndEncodePassword(String account, String encodePassword);


    /**
     * 查找用户名和加密密码
     *
     * @param username
     * @param encodePassword
     * @return
     */
    User findByUsernameAndEncodePassword(String username, String encodePassword);


    /**
     * 根据手机号查找
     *
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);


    /**
     * 根据用户名查找
     *
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * 根据用户ID查找
     *
     * @param userId
     * @return
     */
    User findByUserId(Long userId);


    /**
     * 根据用户ID显示头像
     *
     * @param userId
     * @return
     */
    String findAvatarByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
