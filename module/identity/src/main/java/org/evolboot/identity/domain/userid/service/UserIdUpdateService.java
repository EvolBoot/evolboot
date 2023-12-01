package org.evolboot.identity.domain.userid.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.userid.dto.UserIdRequestBase;
import org.evolboot.identity.domain.userid.entity.UserId;
import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import org.springframework.stereotype.Service;

/**
 * UserId
 *
 * @author evol
 */
@Slf4j
@Service
public class UserIdUpdateService {

    private final UserIdRepository repository;
    private final UserIdSupportService supportService;

    protected UserIdUpdateService(UserIdRepository repository, UserIdSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }


    public void execute(Long id, Request request) {
        UserId userId = supportService.findById(id);
        repository.save(userId);
    }

    public static class Request extends UserIdRequestBase {
    }

}
