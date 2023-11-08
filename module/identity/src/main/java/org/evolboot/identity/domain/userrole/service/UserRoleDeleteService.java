package org.evolboot.identity.domain.userrole.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserRoleDeleteService  {
    
    private final UserRoleRepository repository;
    
    private final UserRoleSupportService supportService;
    
    protected UserRoleDeleteService(UserRoleRepository repository, UserRoleSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    void execute(Long userId) {
        repository.findById(userId).ifPresent(userRole -> {
            repository.deleteById(userId);
        });
    }
}
