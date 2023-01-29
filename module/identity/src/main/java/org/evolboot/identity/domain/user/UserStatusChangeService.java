package org.evolboot.identity.domain.user;

import org.evolboot.identity.acl.port.SecurityAccessTokenPort;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.identity.domain.user.shared.UserStatus;
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

    private final SecurityAccessTokenPort securityAccessTokenPort;

    public UserStatusChangeService(UserRepository repository, SecurityAccessTokenPort securityAccessTokenPort) {
        super(repository);
        this.securityAccessTokenPort = securityAccessTokenPort;
    }

    public void execute(Request request) {
        User user = findById(request.getUserId());
        user.setStatus(request.getStatus());
        if (UserStatus.LOCK.equals(request.getStatus())) {
            securityAccessTokenPort.kickOut(request.getUserId());
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


