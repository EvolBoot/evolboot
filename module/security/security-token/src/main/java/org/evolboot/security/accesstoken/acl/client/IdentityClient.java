package org.evolboot.security.accesstoken.acl.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.identity.domain.user.UserRegisterService;

import java.util.List;

/**
 * @author evol
 */
public interface IdentityClient {

    UserInfo findByUsernameOrMobileOrEmailAndEncodePassword(String value, String encodePassword);

    UserInfo findByMobile(String mobile);

    UserInfo register(UserRegisterService.Request request);

    /**
     * @param request
     * @return
     */
    UserInfo findByMobileAndSmsCode(UserRegisterService.Request request);

    @Getter
    @Setter
    @AllArgsConstructor
    class UserInfo {


        private Long userId;

        private Boolean enableGoogleAuthenticator;

        private String googleAuthenticatorSecret;

        private List<String> authorities;
    }
}
