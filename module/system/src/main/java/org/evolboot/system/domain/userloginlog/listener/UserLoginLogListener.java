package org.evolboot.system.domain.userloginlog.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.event.sessionuser.UserLoginEvent;
import org.evolboot.system.domain.userloginlog.entity.UserLoginLog;
import org.evolboot.system.domain.userloginlog.repository.UserLoginLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserLoginLogListener {

    private final UserLoginLogRepository repository;

    public UserLoginLogListener(UserLoginLogRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onUserLoginEvent(UserLoginEvent event) {
        UserLoginLog log = new UserLoginLog(
                event.getUserId(), event.getLoginToken(), event.getLoginIp(), ""
        );
        repository.save(log);
    }

}
