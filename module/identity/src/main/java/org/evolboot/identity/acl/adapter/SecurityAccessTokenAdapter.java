package org.evolboot.identity.acl.adapter;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.acl.client.SecurityAccessTokenClient;
import org.evolboot.security.api.SecurityAccessTokenAppService;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class SecurityAccessTokenAdapter implements SecurityAccessTokenClient {

    private final SecurityAccessTokenAppService securityAccessTokenAppService;

    public SecurityAccessTokenAdapter(SecurityAccessTokenAppService securityAccessTokenAppService) {
        this.securityAccessTokenAppService = securityAccessTokenAppService;
    }

    @Override
    public void kickOut(Long userId) {
        securityAccessTokenAppService.kickOut(userId);
    }
}
