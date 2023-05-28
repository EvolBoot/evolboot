package org.evolboot.identity.domain.userrole.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.identity.domain.userrole.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author evol
 */
@Service
@Slf4j
public class UserRoleQueryService {

    private final UserRoleRepository repository;

    public UserRoleQueryService(UserRoleRepository repository) {
        this.repository = repository;
    }

    public List<UserRole> findByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }


}
