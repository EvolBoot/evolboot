package org.evolboot.identity.domain.user;

import org.evolboot.core.event.EventPublisher;
import org.evolboot.shared.event.user.UserIdentityChangeEvent;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.shared.lang.UserIdentity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserIdentityChangeService extends UserSupportService {

    private final EventPublisher eventPublisher;

    public UserIdentityChangeService(UserRepository repository, EventPublisher eventPublisher) {
        super(repository);
        this.eventPublisher = eventPublisher;
    }

    void addUserIdentity(Long userId, UserIdentity userIdentity) {
        User user = findById(userId);
        user.addUserIdentity(userIdentity);
        repository.save(user);
        eventPublisher.publishEvent(new UserIdentityChangeEvent(userId, user.getUserIdentity()));
    }

    void removeUserIdentity(Long userId, UserIdentity userIdentity) {
        User user = findById(userId);
        user.removeUserIdentity(userIdentity);
        repository.save(user);
        eventPublisher.publishEvent(new UserIdentityChangeEvent(userId, user.getUserIdentity()));
    }


}
