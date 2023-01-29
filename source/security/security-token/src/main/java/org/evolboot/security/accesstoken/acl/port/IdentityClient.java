package org.evolboot.security.accesstoken.acl.port;

import org.evolboot.identity.domain.user.UserRegisterService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author evol
 */
public interface IdentityClient {

    UserInfo findByUsernameOrMobileOrEmailAndEncodePassword(String value, String encodePassword);

    UserInfo findByMobile(String mobile);

    UserInfo register(UserRegisterService.Request request);

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
