package org.evolboot.identity.domain.user;

import org.evolboot.core.data.Page;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.dto.UserQueryRequest;
import org.evolboot.identity.domain.user.password.UserEncryptPasswordService;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.identity.domain.user.service.*;

import org.evolboot.identity.domain.user.entity.User;


import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 用户
 *
 * @author evol
 * @date 2023-06-14 15:03:11
 */
@Slf4j
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository repository;

    private final UserSupportService supportService;

    private final UserEncryptPasswordService userEncryptPasswordService;


    protected UserQueryServiceImpl(UserRepository repository, UserSupportService supportService, UserEncryptPasswordService userEncryptPasswordService) {
        this.repository = repository;
        this.supportService = supportService;
        this.userEncryptPasswordService = userEncryptPasswordService;
    }


    @Override
    public User findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }


    @Override
    public List<User> findAll(UserQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<User> page(UserQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<User> findOne(UserQueryRequest query) {
        return repository.findOne(query);
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return repository.findById(userId).isPresent();
    }


    @Override
    public String findAvatarByUserId(Long userId) {
        return repository.findAvatarByUserId(userId);
    }


    @Override
    public User findByUserId(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
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
    public boolean existsByMobile(String mobile) {
        return repository.existsByMobile(mobile);
    }

    @Override
    public User findByUsernameOrMobileOrEmailAndEncodePassword(String account, String encodePassword) {
        User user = repository.findByUsernameOrMobileOrEmail(account).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        Assert.isTrue(user.verifyPassword(userEncryptPasswordService.toReversiblePassword(encodePassword).toOriginalPassword()), IdentityI18nMessage.User.passwordWrong());
        return user;
    }


    @Override
    public User findByUsernameAndEncodePassword(String username, String encodePassword) {
        User user = repository.findByUsername(username).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        Assert.isTrue(user.verifyPassword(userEncryptPasswordService.toReversiblePassword(encodePassword).toOriginalPassword()), IdentityI18nMessage.User.passwordWrong());
        return user;
    }


}
