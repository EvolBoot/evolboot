package org.evolboot.identity.domain.userrole.service;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.userrole.UserRole;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户角色
 *
 * @author evol
 * 
 */
@Slf4j
public abstract class UserRoleSupportService {

    protected final UserRoleRepository repository;

    protected UserRoleSupportService(UserRoleRepository repository) {
        this.repository = repository;
    }

    public UserRole findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.UserRole.notFound()));
    }


}
