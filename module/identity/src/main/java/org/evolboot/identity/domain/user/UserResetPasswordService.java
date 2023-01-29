package org.evolboot.identity.domain.user;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.acl.port.IdentityCaptchaPort;
import org.evolboot.identity.domain.user.password.ReversiblePassword;
import org.evolboot.identity.domain.user.password.UserEncryptPasswordService;
import org.evolboot.identity.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserResetPasswordService extends UserSupportService {

    private final IdentityCaptchaPort identityCaptchaPort;
    private final UserEncryptPasswordService userEncryptPasswordService;

    public UserResetPasswordService(UserRepository repository, IdentityCaptchaPort identityCaptchaPort, UserEncryptPasswordService userEncryptPasswordService) {
        super(repository);
        this.identityCaptchaPort = identityCaptchaPort;
        this.userEncryptPasswordService = userEncryptPasswordService;
    }

    public User execute(Request request) {
        User user = null;
        if (ExtendObjects.isNotBlank(request.getEmail())) {
            identityCaptchaPort.verifyEmailCaptchaIsTrue(request.getEmail(),
                    request.getCaptchaCode(),
                    request.getCaptchaToken(),
                    null);
            user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        }
        if (ExtendObjects.isNotBlank(request.getMobile())) {
            identityCaptchaPort.verifyMobileCaptchaIsTrue(
                    request.getMobilePrefix(),
                    request.getMobile(),
                    request.getCaptchaCode(),
                    request.getCaptchaToken(),
                    null
            );
            user = repository.findByMobile(request.getMobile()).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
        }
        Assert.notNull(user, IdentityI18nMessage.User.userNotFound());
        ReversiblePassword reversiblePassword = userEncryptPasswordService.toReversiblePassword(request.getEncodePassword());
        user.resetPassword(reversiblePassword.toOriginalPassword());
        repository.save(user);
        return user;
    }

    @Getter
    @Builder
    @Setter
    @AllArgsConstructor
    public static class Request {
        private String email;
        private String mobilePrefix;
        private String mobile;
        private String encodePassword;
        private String captchaToken;
        private String captchaCode;
    }

}
