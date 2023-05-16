package org.evolboot.schedule.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.schedule.acl.client.IdentityClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Slf4j
@Component
@ConditionalOnProperty("evolpn.schedule.enable")
public class UserIdUpdateService {

    private final IdentityClient identityClient;


    public UserIdUpdateService(IdentityClient identityClient) {
        this.identityClient = identityClient;
    }

    /**
     * 定时更新用户ID
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 5 * 60 * 1000)
    public void userIdUpdate() throws Exception {
        identityClient.userIdUpdate();
    }


    /**
     * 定时更新用户ID
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 5 * 60 * 1000)
    public void userIdUpdateCache() throws Exception {
        identityClient.userIdUpdateCache();
    }


}
