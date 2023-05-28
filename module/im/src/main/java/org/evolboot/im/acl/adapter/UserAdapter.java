package org.evolboot.im.acl.adapter;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.acl.client.UserClient;
import org.evolboot.shared.api.identity.user.UserServiceApi;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserAdapter implements UserClient {

    private final UserServiceApi userAppService;

    public UserAdapter(UserServiceApi userServiceApi) {
        this.userAppService = userServiceApi;
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return userAppService.existsByUserId(userId);
    }
}
