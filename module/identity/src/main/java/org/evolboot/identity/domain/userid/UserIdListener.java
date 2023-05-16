package org.evolboot.identity.domain.userid;

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
public class UserIdListener extends UserIdSupportService {

    protected UserIdListener(UserIdRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
