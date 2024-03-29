package org.evolboot.schedule.acl.adapter;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.userid.UserIdAppService;
import org.evolboot.schedule.acl.client.IdentityClient;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class IdentityAdapter implements IdentityClient {

    private final UserIdAppService userIdAppService;

    public IdentityAdapter(UserIdAppService userIdAppService) {
        this.userIdAppService = userIdAppService;
    }


    @Override
    public void userIdUpdate() {
        userIdAppService.checkAndAdd();
    }

    @Override
    public void userIdUpdateCache() {
        userIdAppService.checkAndAddCache();
    }


}
