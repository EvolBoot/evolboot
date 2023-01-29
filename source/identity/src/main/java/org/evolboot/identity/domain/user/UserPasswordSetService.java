package org.evolboot.identity.domain.user;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.password.ReversiblePassword;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
public class UserPasswordSetService {

    private final UserRepository repository;

    public UserPasswordSetService(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(Long userId, ReversiblePassword password) {
        User user = repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        user.resetPassword(password.toOriginalPassword());
        repository.save(user);
    }
}
