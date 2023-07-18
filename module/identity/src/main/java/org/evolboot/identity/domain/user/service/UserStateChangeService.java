package org.evolboot.identity.domain.user.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.acl.client.SecurityAccessTokenClient;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.entity.UserState;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserStateChangeService extends UserSupportService {

    private final SecurityAccessTokenClient securityAccessTokenClient;

    public UserStateChangeService(UserRepository repository, SecurityAccessTokenClient securityAccessTokenClient) {
        super(repository);
        this.securityAccessTokenClient = securityAccessTokenClient;
    }

    public void execute(Request request) {
        User user = findById(request.getId());
        user.setState(request.getState());
        if (UserState.LOCK.equals(request.getState())) {
            securityAccessTokenClient.kickOut(request.getId());
        }
        repository.save(user);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        private Long id;

        private UserState state;

    }
}


