package org.evolboot.identity.domain.userid.service;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.userid.UserId;
import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * UserId
 *
 * @author evol
 * 
 */
@Slf4j
public abstract class UserIdSupportService {

    protected final UserIdRepository repository;

    protected UserIdSupportService(UserIdRepository repository) {
        this.repository = repository;
    }

    public UserId findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.UserId.notFound()));
    }

}
