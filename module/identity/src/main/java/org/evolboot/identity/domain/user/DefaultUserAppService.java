package org.evolboot.identity.domain.user;

import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.data.Page;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.GoogleAuthenticator;
import org.evolboot.identity.domain.user.service.*;
import org.evolboot.shared.event.user.UserDeleteEvent;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.acl.client.SecurityAccessTokenClient;
import org.evolboot.identity.domain.user.password.UserEncryptPasswordService;
import org.evolboot.identity.domain.user.relation.RelationAppService;
import org.evolboot.identity.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class DefaultUserAppService implements UserAppService {

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

    private final UserStatusChangeService userStatusChangeService;

    private final SecurityAccessTokenClient securityAccessTokenClient;

    public DefaultUserAppService(UserRegisterService userRegisterService, UserPasswordUpdateService userPasswordUpdateService, UserRepository repository, UserUpdateService userUpdateService, UserPasswordSetService userPasswordSetService, UserCreateFactory userCreateFactory, EventPublisher eventPublisher, UserEncryptPasswordService userEncryptPasswordService, RelationAppService relationAppService, UserRelationRefactorService userRelationRefactorService, UserChangeInviterUserIdService userChangeInviterUserIdService, UserResetPasswordService userResetPasswordService, UserSecurityPasswordUpdateService userSecurityPasswordUpdateService, UserSecurityPasswordResetService userSecurityPasswordResetService, UserStatusChangeService userStatusChangeService, SecurityAccessTokenClient securityAccessTokenClient) {
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
        this.userStatusChangeService = userStatusChangeService;
        this.securityAccessTokenClient = securityAccessTokenClient;
    }


    @Transactional
    @Override
    public void resetPassword(Long userId, String encodePassword) {
        userPasswordSetService.execute(userId, userEncryptPasswordService.toReversiblePassword(encodePassword));
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
    public User create(UserCreateFactory.Request request) {
        return userCreateFactory.create(request);
    }


    @Override
    public String findAvatarByUserId(Long userId) {
        return repository.findAvatarByUserId(userId);
    }

    @Transactional
    @Override
    public void delete(Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
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

    @Override
    public User findByUserId(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
    }

    @Transactional
    @Override
    public void update(Long userId, UserUpdateService.Request request) {
        userUpdateService.execute(userId, request);
    }

    @Transactional
    @Override
    public void lock(Long userId) {
        userStatusChangeService.execute(new UserStatusChangeService.Request(userId, UserStatus.LOCK));
    }


    @Transactional
    @Override
    public void active(Long userId) {
        userStatusChangeService.execute(new UserStatusChangeService.Request(userId, UserStatus.ACTIVE));
    }

    @Override
    @Transactional
    public void changeStatus(UserStatusChangeService.Request request) {
        userStatusChangeService.execute(request);
    }


    @Override
    public Page<User> page(UserQuery query) {
        return repository.page(query);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User findByMobile(String mobile) {
        User user = repository.findByMobile(mobile).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        return user;
    }

    @Override
    public User findByUsernameAndEncodePassword(String username, String encodePassword) {
        User user = repository.findByUsername(username).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        Assert.isTrue(user.verifyPassword(userEncryptPasswordService.toReversiblePassword(encodePassword).toOriginalPassword()), IdentityI18nMessage.User.passwordWrong());
        return user;
    }

    @Override
    public User findByUsernameOrMobileOrEmailAndEncodePassword(String account, String encodePassword) {
        User user = repository.findByUsernameOrMobileOrEmail(account).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        Assert.isTrue(user.verifyPassword(userEncryptPasswordService.toReversiblePassword(encodePassword).toOriginalPassword()), IdentityI18nMessage.User.passwordWrong());
        return user;
    }

    @Override
    public void setUserType(Long userId, UserType userType) {
        User user = findByUserId(userId);
        user.setUserType(userType);
        repository.save(user);
    }

    @Override
    public boolean existsByMobile(String mobile) {
        return repository.existsByMobile(mobile);
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
        User user = findByUserId(userId);
        String generateSecretKey = GoogleAuthenticator.generateSecretKey();
        user.setGoogleAuthSecret(generateSecretKey);
        repository.save(user);
        return user;
    }
}
