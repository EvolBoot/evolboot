package org.evolboot.security.api;

import org.evolboot.shared.security.EvolSession;

/**
 * @author evol
 */
public interface SecurityAccessTokenAppService {

    EvolSession findByToken(String tokenValue);

    void logout(String tokenValue);

    void kickOut(Long userId);

    LoginService.Response login(LoginService.Request request);

    void refresh(String token);


}
