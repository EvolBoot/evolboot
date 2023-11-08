package org.evolboot.identity.domain.user.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.shared.event.user.UserIdentityChangeEvent;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserIdentityChangeService {

    private final UserRepository repository;

    private final UserSupportService supportService;

    private final EventPublisher eventPublisher;

    public UserIdentityChangeService(UserRepository repository, UserSupportService supportService, EventPublisher eventPublisher) {
        this.repository = repository;
        this.supportService = supportService;
        this.eventPublisher = eventPublisher;
    }

    void addUserIdentity(Long userId, UserIdentity userIdentity) {
        User user = supportService.findById(userId);
        user.addUserIdentity(userIdentity);
        repository.save(user);
        eventPublisher.publishEvent(new UserIdentityChangeEvent(userId, user.getUserIdentity()));
    }

    void removeUserIdentity(Long userId, UserIdentity userIdentity) {
        User user = supportService.findById(userId);
        user.removeUserIdentity(userIdentity);
        repository.save(user);
        eventPublisher.publishEvent(new UserIdentityChangeEvent(userId, user.getUserIdentity()));
    }


}
