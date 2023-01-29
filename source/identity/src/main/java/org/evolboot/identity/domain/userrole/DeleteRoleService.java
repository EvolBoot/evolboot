package org.evolboot.identity.domain.userrole;

import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
