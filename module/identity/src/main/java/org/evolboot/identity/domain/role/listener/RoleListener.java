package org.evolboot.identity.domain.role.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.role.service.RoleDeletePermissionService;
import org.evolboot.shared.event.permission.PermissionDeleteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class RoleListener {

    private final RoleDeletePermissionService roleDeletePermissionService;

    public RoleListener(RoleDeletePermissionService roleDeletePermissionService) {
        this.roleDeletePermissionService = roleDeletePermissionService;
    }

    @EventListener
    public void onPermissionDeleteEvent(PermissionDeleteEvent event) {
        roleDeletePermissionService.execute(event.getPermissionId());
    }


}
