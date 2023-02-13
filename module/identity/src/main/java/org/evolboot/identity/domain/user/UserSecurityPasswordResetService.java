package org.evolboot.identity.domain.user;


import org.evolboot.core.service.crypto.rsa.RsaService;
import org.evolboot.identity.acl.client.IdentityCaptchaClient;
import org.evolboot.identity.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 交易密码
 *
 * @author evol
 *
 */
@Slf4j
@Service
public class UserSecurityPasswordResetService extends UserSupportService {

    private final RsaService rsaService;
    private final IdentityCaptchaClient identityCaptchaClient;

    public UserSecurityPasswordResetService(UserRepository repository, RsaService rsaService, IdentityCaptchaClient identityCaptchaClient) {
        super(repository);
        this.rsaService = rsaService;
        this.identityCaptchaClient = identityCaptchaClient;
    }

    /**
     * 重置交易密码
     *
     * @param request
     * @return
     */
    public User execute(Request request) {
        User user = findById(request.getUserId());
        if (ValidationType.EMAIL_CAPTCHA.equals(request.getValidationType())) {
            identityCaptchaClient.verifyEmailCaptchaIsTrue(user.getEmail(), request.getCaptchaCode(), request.getCaptchaToken(), request.getInternalCode());
        } else {
            identityCaptchaClient.verifyMobileCaptchaIsTrue(
                    user.getMobilePrefix(),
                    user.getMobile(),
                    request.getCaptchaCode(),
                    request.getCaptchaToken(),
                    request.getInternalCode()
            );
        }
        user.resetSecurityPassword(rsaService.privateDecryptStr(request.getEncodePassword()));
        repository.save(user);
        return user;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private Long userId;
        private ValidationType validationType;
        private String captchaToken;
        private String captchaCode;
        private String encodePassword;
        private String internalCode;
    }

    public enum ValidationType {
        MOBILE_CAPTCHA, EMAIL_CAPTCHA;
    }

}
