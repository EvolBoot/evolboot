package org.evolboot.identity.domain.userrole.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.evolboot.identity.domain.userrole.service.DeleteRoleService;
import org.evolboot.identity.domain.userrole.service.UserRoleDeleteService;
import org.evolboot.identity.domain.userrole.service.UserRoleSupportService;
import org.springframework.stereotype.Service;

/**
 * 用户角色
 *
 * @author evol
 */
@Service
@Slf4j
@Deprecated
public class UserRoleListener {

    private final UserRoleRepository repository;

    private final UserRoleSupportService supportService;


    private final DeleteRoleService deleteRoleService;
    private final UserRoleDeleteService userRoleDeleteService;

    protected UserRoleListener(UserRoleRepository repository,
                               UserRoleSupportService supportService, DeleteRoleService deleteRoleService,
                               UserRoleDeleteService userRoleDeleteService) {
        this.repository = repository;
        this.supportService = supportService;
        this.deleteRoleService = deleteRoleService;
        this.userRoleDeleteService = userRoleDeleteService;
    }


//    @EventListener
//    public void on(RoleDeleteEvent event) {
//        deleteRoleService.execute(event.getRoleId());
//    }

}
