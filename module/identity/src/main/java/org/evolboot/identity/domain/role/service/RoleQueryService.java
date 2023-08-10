package org.evolboot.identity.domain.role.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
@Service
@Slf4j
//TODO 多语言
public class RoleQueryService {

    private final RoleRepository repository;

    public RoleQueryService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    public boolean exist(Long id) {
        return repository.existsById(id);
    }

    public void mustExist(Collection<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }
        ids.forEach(id -> Assert.isTrue(exist(id),id + "角色不存在"));
     }
}
