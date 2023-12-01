package org.evolboot.system.domain.userloginlog;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.system.domain.userloginlog.entity.UserLoginLog;
import org.evolboot.system.domain.userloginlog.repository.UserLoginLogRepository;
import org.evolboot.system.domain.userloginlog.dto.UserLoginLogQueryRequest;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserLoginLogAppServiceImpl implements UserLoginLogAppService {

    private final UserLoginLogRepository repository;

    public UserLoginLogAppServiceImpl(UserLoginLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<UserLoginLog> page(UserLoginLogQueryRequest query) {
        return repository.page(query);
    }


}
