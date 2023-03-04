package org.evolboot.identity.domain.user.service;

import org.evolboot.identity.acl.client.SecurityAccessTokenClient;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.UserStatus;
import org.evolboot.identity.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserStatusChangeService extends UserSupportService {

    private final SecurityAccessTokenClient securityAccessTokenClient;

    public UserStatusChangeService(UserRepository repository, SecurityAccessTokenClient securityAccessTokenClient) {
        super(repository);
        this.securityAccessTokenClient = securityAccessTokenClient;
    }

    public void execute(Request request) {
        User user = findById(request.getUserId());
        user.setStatus(request.getStatus());
        if (UserStatus.LOCK.equals(request.getStatus())) {
            securityAccessTokenClient.kickOut(request.getUserId());
        }
        repository.save(user);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        private Long userId;

        private UserStatus status;

    }
}


