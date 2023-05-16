package org.evolboot.identity.domain.user.service;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class UserSupportService {

    protected final UserRepository repository;

    public UserSupportService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
    }


    public void requireUsernameIsNoExist(String username) {
        Assert.notBlank(username, IdentityI18nMessage.User.userNotFound());
        Assert.isTrue(repository.findByUsername(username).isEmpty(), IdentityI18nMessage.User.usernameAlreadyExisted());
    }


    public void requireMobileIsNoExist(String mobile) {
        Assert.notBlank(mobile, IdentityI18nMessage.User.mobileNotEmpty());
        Assert.isTrue(repository.findByMobile(mobile).isEmpty(), IdentityI18nMessage.User.mobileAlreadyExisted());
    }

    public void requireEmailIsNoExist(String email) {
        Assert.notBlank(email, IdentityI18nMessage.User.emailNotEmpty());
        Assert.isTrue(repository.findByEmail(email).isEmpty(), IdentityI18nMessage.User.emailAlreadyExisted());
    }

}
