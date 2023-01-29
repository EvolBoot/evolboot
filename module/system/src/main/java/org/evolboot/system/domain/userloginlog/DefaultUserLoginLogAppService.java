package org.evolboot.system.domain.userloginlog;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.userloginlog.repository.UserLoginLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class DefaultUserLoginLogAppService implements UserLoginLogAppService {

    private final UserLoginLogRepository repository;

    public DefaultUserLoginLogAppService(UserLoginLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<UserLoginLog> page(UserLoginLogQuery query) {
        return repository.page(query);
    }


}
