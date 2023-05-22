package org.evolboot.security.accesstoken.domain;

import org.evolboot.identity.domain.user.service.UserRegisterService;
import org.evolboot.security.accesstoken.acl.client.IdentityClient;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Deprecated
public class RegisterUserAndGetAccessToken {

    private final IdentityClient identityClient;

    public RegisterUserAndGetAccessToken(IdentityClient identityClient) {
        this.identityClient = identityClient;
    }

    public AccessToken execute(UserRegisterService.Request request) {
        IdentityClient.UserInfo userInfo = identityClient.register(request);
        return new AccessToken(userInfo.getUserId(), userInfo.getAuthorities());
    }


}
