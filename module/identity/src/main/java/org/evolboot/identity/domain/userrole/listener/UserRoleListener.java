package org.evolboot.identity.domain.userrole.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.evolboot.identity.domain.userrole.service.DeleteRoleService;
import org.evolboot.identity.domain.userrole.service.UserRoleDeleteService;
import org.evolboot.identity.domain.userrole.service.UserRoleSupportService;
import org.evolboot.shared.event.role.RoleDeleteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 用户角色
 *
 * @author evol
 */
@Service
@Slf4j
public class UserRoleListener extends UserRoleSupportService {

    private final DeleteRoleService deleteRoleService;
    private final UserRoleDeleteService userRoleDeleteService;

    protected UserRoleListener(UserRoleRepository repository,
                               DeleteRoleService deleteRoleService,
                               UserRoleDeleteService userRoleDeleteService) {
        super(repository);
        this.deleteRoleService = deleteRoleService;
        this.userRoleDeleteService = userRoleDeleteService;
    }


    @EventListener
    public void on(RoleDeleteEvent event) {
        deleteRoleService.execute(event.getRoleId());
    }

}
