package org.evolboot.identity.domain.user.service;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.password.ReversiblePassword;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class UserPasswordUpdateService {

    private final UserRepository repository;

    public UserPasswordUpdateService(UserRepository repository) {
        this.repository = repository;
    }

    public void updatePassword(Long userId,
                               ReversiblePassword oldPassword,
                               ReversiblePassword newPassword,
                               ReversiblePassword confirmPassword) {
        Assert.isTrue(newPassword.toOriginalPassword().equals(confirmPassword.toOriginalPassword()), IdentityI18nMessage.User.confirmPasswordError());
        User user = repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        user.updatePassword(oldPassword.toOriginalPassword(), newPassword.toOriginalPassword());
        repository.save(user);
    }
}
