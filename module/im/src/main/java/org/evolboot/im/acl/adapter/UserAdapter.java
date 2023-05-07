package org.evolboot.im.acl.adapter;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.im.acl.client.UserClient;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserAdapter implements UserClient {

    private final UserAppService userAppService;

    public UserAdapter(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return userAppService.existsByUserId(userId);
    }
}
