package org.evolboot.identity.domain.userid;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.userid.service.UserIdCreateFactory;
import org.evolboot.identity.domain.userid.service.UserIdGetNextService;
import org.evolboot.identity.domain.userid.service.UserIdSupportService;
import org.evolboot.identity.domain.userid.service.UserIdUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserId
 *
 * @author evol
 * 
 */
@Slf4j
@Service
public class DefaultUserIdAppService extends UserIdSupportService implements UserIdAppService {


    private final int step = 10000;
    private final UserIdCreateFactory factory;
    private final UserIdUpdateService updateService;
    private final UserIdGetNextService userIdGetNextService;

    protected DefaultUserIdAppService(UserIdRepository repository, UserIdCreateFactory factory, UserIdUpdateService updateService, UserIdGetNextService userIdGetNextService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
        this.userIdGetNextService = userIdGetNextService;
    }

    @Override
    @Transactional
    public void create(int generateNum) {
        factory.execute(generateNum);
    }


    @Override
    @Transactional
    public void update(Long id, UserIdUpdateService.Request request) {
        updateService.execute(id, request);
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
    public List<UserId> findAll(UserIdQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<UserId> page(UserIdQuery query) {
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
        Long count = repository.countByStatus(false);
        if (count < step / 2) {
            log.info("用户ID:往数据库里面增加用户ID");
            factory.execute(step);
        }
    }

}
