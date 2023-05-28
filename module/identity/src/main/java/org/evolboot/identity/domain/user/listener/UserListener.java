package org.evolboot.identity.domain.user.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.user.service.UserRoleDeleteService;
import org.evolboot.shared.event.role.RoleDeleteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserListener {

    private final UserRoleDeleteService userRoleDeleteService;

    public UserListener(UserRoleDeleteService userRoleDeleteService) {
        this.userRoleDeleteService = userRoleDeleteService;
    }

    @EventListener
    @Transactional
    public void on(RoleDeleteEvent event) {
        userRoleDeleteService.deleteRoleId(event.getRoleId());
    }


}
