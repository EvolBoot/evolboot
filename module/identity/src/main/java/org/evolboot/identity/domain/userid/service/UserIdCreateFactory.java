package org.evolboot.identity.domain.userid.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.userid.entity.UserId;
import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserId
 *
 * @author evol
 */
@Slf4j
@Service
public class UserIdCreateFactory {

    private final UserIdRepository repository;
    private final UserIdSupportService supportService;

    protected UserIdCreateFactory(UserIdRepository repository, UserIdSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(int generateNum) {
        List<UserId> userIds = Lists.newArrayList();
        for (int i = 0; i < generateNum; i++) {
            userIds.add(new UserId());
        }
        repository.saveAll(userIds);
    }


}
