package org.evolboot.identity.domain.userrole.service;

import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class DeleteRoleService  {

    protected final UserRoleRepository repository;


    protected DeleteRoleService(UserRoleRepository repository) {
        this.repository = repository;
    }

    public void execute(Long roleId) {
        repository.deleteByRoleId(roleId);

    }

}
