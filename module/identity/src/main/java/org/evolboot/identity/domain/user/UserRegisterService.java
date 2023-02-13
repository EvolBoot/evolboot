package org.evolboot.identity.domain.user;

import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.acl.client.IdentityCaptchaClient;
import org.evolboot.identity.acl.client.IdentityConfigClient;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.identity.domain.user.shared.UserType;
import org.evolboot.shared.lang.DeviceType;
import org.evolboot.shared.lang.UserIdentity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class UserRegisterService extends UserSupportService {

    private final UserCreateFactory factory;
    private final IdentityCaptchaClient identityCaptchaClient;
    private final IdentityConfigClient identityConfigClient;


    public UserRegisterService(UserRepository repository, UserCreateFactory factory, IdentityCaptchaClient identityCaptchaClient, IdentityConfigClient identityConfigClient) {
        super(repository);
        this.factory = factory;
        this.identityCaptchaClient = identityCaptchaClient;
        this.identityConfigClient = identityConfigClient;
    }

    public User register(Request request) {

        Boolean enableRegisterSms = identityConfigClient.enableRegisterSms();
        Boolean enableRegisterEmailValidation = identityConfigClient.enableRegisterEmailValidation();

        if (enableRegisterSms) {
            if (ExtendObjects.isNotBlank(request.getMobile())) {
                identityCaptchaClient.verifyMobileCaptchaIsTrue(request.getMobilePrefix(),
                        request.getMobile(),
                        request.getCaptchaCode(),
                        request.getCaptchaToken(),
                        null);
            }
        }

        if (enableRegisterEmailValidation) {
            if (ExtendObjects.isNotBlank(request.getEmail())) {
                identityCaptchaClient.verifyEmailCaptchaIsTrue(request.getEmail(), request.getCaptchaCode(), request.getCaptchaToken(), null);
            }
        }

        //TODO 多语言
        Assert.isTrue(ExtendObjects.isNotBlank(request.getEmail()) ||
                ExtendObjects.isNotBlank(request.getMobile()), "邮箱或者手机号必填一个");
        return factory.create(
                UserCreateFactory.Request.builder()
                        .email(request.getEmail())
                        .mobilePrefix(request.getMobilePrefix())
                        .mobile(request.getMobile())
                        .encodePassword(request.getEncryptPassword())
                        .userIdentity(UserIdentity.ROLE_MEMBER)
                        .userType(UserType.NORMAL)
                        .registerIp(request.getRegisterIp())
                        .inviterUserId(request.getInviterUserId())
                        .deviceType(request.getDeviceType())
                        .build());
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String mobilePrefix;
        private String mobile;
        private String encryptPassword;
        private String captchaToken;
        private String captchaCode;
        private String email;
        private String registerIp;
        private Long inviterUserId;
        private DeviceType deviceType;
    }

}
