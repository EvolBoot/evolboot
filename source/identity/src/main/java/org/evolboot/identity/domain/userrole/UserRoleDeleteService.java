package org.evolboot.identity.domain.userrole;

import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserRoleDeleteService extends UserRoleSupportService {
    protected UserRoleDeleteService(UserRoleRepository repository) {
        super(repository);
    }

    void execute(Long userId) {
        repository.findById(userId).ifPresent(userRole -> {
            repository.deleteById(userId);
        });
    }
}
