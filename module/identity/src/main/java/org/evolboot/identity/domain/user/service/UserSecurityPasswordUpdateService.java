package org.evolboot.identity.domain.user.service;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.password.ReversiblePassword;
import org.evolboot.identity.domain.user.password.UserEncryptPasswordService;
import org.evolboot.identity.domain.user.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


/**
 * @author evol
 * 
 */
@Service
public class UserSecurityPasswordUpdateService {

    private final UserRepository repository;
    private final UserEncryptPasswordService userEncryptPasswordService;

    public UserSecurityPasswordUpdateService(UserRepository repository, UserEncryptPasswordService userEncryptPasswordService) {
        this.repository = repository;
        this.userEncryptPasswordService = userEncryptPasswordService;
    }

    public void execute(Long userId, Request request) {

        ReversiblePassword oldPassword = userEncryptPasswordService.toReversiblePassword(request.getOldPassword());
        ReversiblePassword newPassword = userEncryptPasswordService.toReversiblePassword(request.getNewPassword());
        ReversiblePassword confirmPassword = userEncryptPasswordService.toReversiblePassword(request.getConfirmPassword());

        Assert.isTrue(newPassword.toOriginalPassword().equals(confirmPassword.toOriginalPassword()), IdentityI18nMessage.User.confirmPasswordError());
        User user = repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        user.updateSecurityPassword(oldPassword.toOriginalPassword(), newPassword.toOriginalPassword());
        repository.save(user);
    }

    @Getter
    @Setter
    public static class Request {

        String oldPassword;

        String newPassword;

        String confirmPassword;

    }
}
