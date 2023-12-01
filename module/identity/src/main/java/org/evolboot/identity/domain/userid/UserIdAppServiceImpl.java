package org.evolboot.identity.domain.userid;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userid.dto.UserIdQueryRequest;
import org.evolboot.identity.domain.userid.entity.UserId;
import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import org.evolboot.identity.domain.userid.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserId
 *
 * @author evol
 */
@Slf4j
@Service
public class UserIdAppServiceImpl implements UserIdAppService {


    private final int step = 10000;
    private final UserIdRepository repository;

    private final UserIdSupportService supportService;

    private final UserIdCreateFactory factory;
    private final UserIdUpdateService updateService;
    private final UserIdGetNextService userIdGetNextService;

    protected UserIdAppServiceImpl(UserIdRepository repository, UserIdSupportService supportService, UserIdCreateFactory factory, UserIdUpdateService updateService, UserIdGetNextService userIdGetNextService) {
        this.repository = repository;
        this.supportService = supportService;
        this.factory = factory;
        this.updateService = updateService;
        this.userIdGetNextService = userIdGetNextService;
    }

    @Override
    public UserId findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    @Transactional
    public void create(int generateNum) {
        factory.execute(generateNum);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<UserId> findAll() {
        return repository.findAll();
    }


    @Override
    public List<UserId> findAll(UserIdQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<UserId> page(UserIdQueryRequest query) {
        return repository.page(query);
    }

    @Override
    public Long getNextUserId() {
        return userIdGetNextService.next();
    }

    @Override
    @Transactional
    public void use(Long id) {
        UserId userId = findById(id);
        userId.use();
        repository.save(userId);
    }

    @Override
    @Transactional
    public void checkAndAddCache() {
        userIdGetNextService.checkAndAddCache();
    }

    @Override
    @Transactional
    public void checkAndAdd() {
        Long count = repository.countByState(false);
        if (count < step / 2) {
            log.info("用户ID:往数据库里面增加用户ID");
            factory.execute(step);
            checkAndAddCache();
        }
    }

}
