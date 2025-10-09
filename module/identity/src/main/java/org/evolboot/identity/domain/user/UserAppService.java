package org.evolboot.identity.domain.user;

import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.entity.UserType;
import org.evolboot.identity.domain.user.service.*;
import org.evolboot.shared.lang.CurrentPrincipal;

/**
 * @author evol
 */
public interface UserAppService {

    /**
     * 管理员帮用户重置密码
     *
     * @param currentPrincipal 当前操作者，可以为空
     * @param userId
     * @param encodePassword
     */
    void resetPassword(CurrentPrincipal currentPrincipal, Long userId, String encodePassword);

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
    User create(CurrentPrincipal currentPrincipal, UserCreateFactory.Request request);

    /**
     * 删除过户
     *
     * @param currentPrincipal 当前操作者，可以为空
     * @param userId
     */
    void delete(CurrentPrincipal currentPrincipal, Long userId);

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
     * 更新
     *
     * @param currentPrincipal 当前操作者，可以为空
     * @param request
     */
    void update(CurrentPrincipal currentPrincipal, UserUpdateService.Request request);

    /**
     * 锁定
     *
     * @param currentPrincipal 当前操作者，可以为空
     * @param userId
     */
    void lock(CurrentPrincipal currentPrincipal, Long userId);

    /**
     * 激活
     *
     * @param currentPrincipal 当前操作者，可以为空
     * @param userId
     */
    void active(CurrentPrincipal currentPrincipal, Long userId);

    /**
     * 更改用户状态
     *
     * @param request
     */
    void changeState(CurrentPrincipal currentPrincipal, UserStateChangeService.Request request);

    /**
     * 设置用户类型
     *
     * @param userId
     * @param userType
     */
    void setUserType(Long userId, UserType userType);


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


}
