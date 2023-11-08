package org.evolboot.identity.domain.userid.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import org.evolboot.identity.domain.userid.service.UserIdSupportService;
import org.springframework.stereotype.Service;

/**
 * UserId
 *
 * @author evol
 */
@Service
@Slf4j
public class UserIdListener {

    private final UserIdRepository repository;

    private final UserIdSupportService supportService;

    protected UserIdListener(UserIdRepository repository, UserIdSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
