package org.evolboot.identity.domain.user;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.user.service.*;

import java.util.Optional;

/**
 * @author evol
 */
public interface UserAppService {

    /**
     * 管理员帮用户重置密码
     *
     * @param userId
     * @param encodePassword
     */
    void resetPassword(Long userId, String encodePassword);

    /**
     * 用户自己重置密码
     */
    void resetPassword(UserResetPasswordService.Request request);

    /**
     * 注册
     *
     * @param request 注册信息
     */
    User register(UserRegisterService.Request request);

    /**
     * 创建一个用户
     */
    User create(UserCreateFactory.Request request);


    /**
     * 根据用户ID显示头像
     *
     * @param userId
     * @return
     */
    String findAvatarByUserId(Long userId);

    /**
     * 删除过户
     *
     * @param userId
     */
    void delete(Long userId);

    /**
     * 更新密码
     *
     * @param userId
     * @param oldEncodePassword
     * @param newEncodePassword
     * @param confirmEncodePassword
     */
    void updatePassword(Long userId, String oldEncodePassword, String newEncodePassword, String confirmEncodePassword);


    /**
     * 重置安全密码
     *
     * @param userId
     * @param request
     */
    void updateSecurityPassword(Long userId, UserSecurityPasswordUpdateService.Request request);


    /**
     * 用户重置安全密码
     *
     * @param request
     */
    void resetSecurityPassword(UserSecurityPasswordResetService.Request request);


    /**
     * 根据用户ID查找
     *
     * @param userId
     * @return
     */
    User findByUserId(Long userId);

    /**
     * 更新
     *
     * @param userId
     * @param request
     */
    void update(Long userId, UserUpdateService.Request request);

    /**
     * 锁定
     *
     * @param userId
     */
    void lock(Long userId);

    /**
     * 激活
     *
     * @param userId
     */
    void active(Long userId);

    void changeStatus(UserStatusChangeService.Request request);

    /**
     * 分页
     *
     * @param query
     * @return
     */
    Page<User> page(UserQuery query);

    /**
     * 根据用户名查找
     *
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * 根据手机号查找
     *
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 查找用户名和加密密码
     *
     * @param username
     * @param encodePassword
     * @return
     */
    User findByUsernameAndEncodePassword(String username, String encodePassword);

    /**
     * 查找用户名或者手机号或者邮箱和加密的密码
     *
     * @param account
     * @param encodePassword
     * @return
     */
    User findByUsernameOrMobileOrEmailAndEncodePassword(String account, String encodePassword);

    /**
     * 设置用户类型
     *
     * @param userId
     * @param userType
     */
    void setUserType(Long userId, UserType userType);


    /**
     * 查看手机号是否存在
     *
     * @param mobile
     * @return
     */
    boolean existsByMobile(String mobile);

    /**
     * 是否存在此用户Id
     * @param userId
     * @return
     */
    boolean existsByUserId(Long userId);

    /**
     * 重构用户关系
     * 关系错乱用来维护
     */
    void refactorRelation();

    /**
     * 更改上级邀请人
     *
     * @param userId
     * @param newInviterUserId
     */
    void changeInviterUserId(Long userId, Long newInviterUserId);

    /**
     * 更新谷歌验证器
     *
     * @param userId
     */
    User updateGoogleAuthenticatorSecret(Long userId);

    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<User> findOne(UserQuery query);
}
