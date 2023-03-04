package org.evolboot.identity.domain.userrole.service;

import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.evolboot.identity.domain.userrole.service.UserRoleSupportService;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
public class DeleteRoleService extends UserRoleSupportService {


    protected DeleteRoleService(UserRoleRepository repository) {
        super(repository);
    }

    public void execute(Long roleId) {
        repository.deleteByRoleId(roleId);

    }

}
