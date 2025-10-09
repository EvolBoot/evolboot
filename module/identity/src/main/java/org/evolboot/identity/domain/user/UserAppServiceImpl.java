package org.evolboot.identity.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.GoogleAuthenticator;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.acl.client.SecurityAccessTokenClient;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.entity.UserState;
import org.evolboot.identity.domain.user.entity.UserType;
import org.evolboot.identity.domain.user.password.UserEncryptPasswordService;
import org.evolboot.identity.domain.user.relation.RelationAppService;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.identity.domain.user.service.*;
import org.evolboot.shared.event.user.UserDeleteEvent;
import org.evolboot.shared.lang.CurrentPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserAppServiceImpl implements UserAppService {

    private final UserRepository repository;

    private final UserRegisterService userRegisterService;

    private final UserPasswordUpdateService userPasswordUpdateService;

    private final UserUpdateService userUpdateService;

    private final UserPasswordSetService userPasswordSetService;

    private final UserCreateFactory userCreateFactory;

    private final EventPublisher eventPublisher;

    private final UserEncryptPasswordService userEncryptPasswordService;

    private final RelationAppService relationAppService;

    private final UserRelationRefactorService userRelationRefactorService;

    private final UserChangeInviterUserIdService userChangeInviterUserIdService;

    private final UserResetPasswordService userResetPasswordService;

    private final UserSecurityPasswordUpdateService userSecurityPasswordUpdateService;

    private final UserSecurityPasswordResetService userSecurityPasswordResetService;

    private final UserStateChangeService userStateChangeService;

    private final SecurityAccessTokenClient securityAccessTokenClient;

    public UserAppServiceImpl(UserRegisterService userRegisterService, UserPasswordUpdateService userPasswordUpdateService, UserRepository repository, UserUpdateService userUpdateService, UserPasswordSetService userPasswordSetService, UserCreateFactory userCreateFactory, EventPublisher eventPublisher, UserEncryptPasswordService userEncryptPasswordService, RelationAppService relationAppService, UserRelationRefactorService userRelationRefactorService, UserChangeInviterUserIdService userChangeInviterUserIdService, UserResetPasswordService userResetPasswordService, UserSecurityPasswordUpdateService userSecurityPasswordUpdateService, UserSecurityPasswordResetService userSecurityPasswordResetService, UserStateChangeService userStateChangeService, SecurityAccessTokenClient securityAccessTokenClient) {
        this.userRegisterService = userRegisterService;
        this.repository = repository;
        this.userPasswordUpdateService = userPasswordUpdateService;
        this.userUpdateService = userUpdateService;
        this.userPasswordSetService = userPasswordSetService;
        this.userCreateFactory = userCreateFactory;
        this.eventPublisher = eventPublisher;
        this.userEncryptPasswordService = userEncryptPasswordService;
        this.relationAppService = relationAppService;
        this.userRelationRefactorService = userRelationRefactorService;
        this.userChangeInviterUserIdService = userChangeInviterUserIdService;
        this.userResetPasswordService = userResetPasswordService;
        this.userSecurityPasswordUpdateService = userSecurityPasswordUpdateService;
        this.userSecurityPasswordResetService = userSecurityPasswordResetService;
        this.userStateChangeService = userStateChangeService;
        this.securityAccessTokenClient = securityAccessTokenClient;
    }


    @Transactional
    @Override
    public void resetPassword(CurrentPrincipal currentPrincipal, Long userId, String encodePassword) {
        userPasswordSetService.execute(currentPrincipal, userId, userEncryptPasswordService.toReversiblePassword(encodePassword));
    }

    @Override
    @Transactional
    public void resetPassword(UserResetPasswordService.Request request) {
        userResetPasswordService.execute(request);
    }

    @Transactional
    @Override
    @NoRepeatSubmit
    public User register(UserRegisterService.Request request) {
        return userRegisterService.register(request);
    }

    @Transactional
    @Override
    public User create(CurrentPrincipal currentPrincipal, UserCreateFactory.Request request) {
        return userCreateFactory.create(currentPrincipal, request);
    }


    @Transactional
    @Override
    public void delete(CurrentPrincipal currentPrincipal, Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));

        // 如果 currentPrincipal 存在且有 tenantId，则验证用户必须属于同一租户
        if (ExtendObjects.nonNull(currentPrincipal) && ExtendObjects.nonNull(currentPrincipal.getTenantId())) {
            Assert.isTrue(currentPrincipal.getTenantId().equals(user.getTenantId()), "无权删除其他租户的用户");
        }

        user.delete();
        repository.save(user);
        eventPublisher.publishEvent(new UserDeleteEvent(user.id()));
        securityAccessTokenClient.kickOut(userId);

    }

    @Transactional
    @Override
    public void updatePassword(Long userId, String oldEncodePassword, String newEncodePassword, String confirmEncodePassword) {
        this.userPasswordUpdateService.updatePassword(userId,
                userEncryptPasswordService.toReversiblePassword(oldEncodePassword),
                userEncryptPasswordService.toReversiblePassword(newEncodePassword),
                userEncryptPasswordService.toReversiblePassword(confirmEncodePassword)
        );
    }

    @Override
    @Transactional
    public void updateSecurityPassword(Long userId, UserSecurityPasswordUpdateService.Request request) {
        userSecurityPasswordUpdateService.execute(userId, request);
    }

    @Override
    @Transactional
    public void resetSecurityPassword(UserSecurityPasswordResetService.Request request) {
        userSecurityPasswordResetService.execute(request);
    }


    @Transactional
    @Override
    public void update(CurrentPrincipal currentPrincipal, UserUpdateService.Request request) {
        userUpdateService.execute(currentPrincipal, request);
    }

    @Transactional
    @Override
    public void lock(CurrentPrincipal currentPrincipal, Long userId) {
        userStateChangeService.execute(currentPrincipal, new UserStateChangeService.Request(userId, UserState.LOCK));
    }


    @Transactional
    @Override
    public void active(CurrentPrincipal currentPrincipal, Long userId) {
        userStateChangeService.execute(currentPrincipal, new UserStateChangeService.Request(userId, UserState.ACTIVE));
    }

    @Override
    @Transactional
    public void changeState(CurrentPrincipal currentPrincipal, UserStateChangeService.Request request) {
        userStateChangeService.execute(currentPrincipal, request);
    }


    @Override
    public void setUserType(Long userId, UserType userType) {
        User user = repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        user.setUserType(userType);
        repository.save(user);
    }


    @Override
    @Transactional
    public void refactorRelation() {
        userRelationRefactorService.execute();
    }

    @Override
    @Transactional
    public void changeInviterUserId(Long userId, Long newInviterUserId) {
        userChangeInviterUserIdService.execute(userId, newInviterUserId);
    }

    @Override
    @Transactional
    public User updateGoogleAuthenticatorSecret(Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        String generateSecretKey = GoogleAuthenticator.generateSecretKey();
        user.setGoogleAuthSecret(generateSecretKey);
        repository.save(user);
        return user;
    }

}
