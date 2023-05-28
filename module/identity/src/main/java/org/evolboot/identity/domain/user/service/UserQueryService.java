package org.evolboot.identity.domain.user.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserQueryService {

    private final UserRepository userRepository;

    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.User.userNotFound()));
    }

}
