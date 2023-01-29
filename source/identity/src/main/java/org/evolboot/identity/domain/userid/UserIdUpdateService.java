package org.evolboot.identity.domain.userid;

import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * UserId
 *
 * @author evol
 * 
 */
@Slf4j
@Service
public class UserIdUpdateService extends UserIdSupportService {
    protected UserIdUpdateService(UserIdRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        UserId userId = findById(id);
        repository.save(userId);
    }

    public static class Request extends UserIdRequestBase {
    }

}
