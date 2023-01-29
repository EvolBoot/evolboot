package org.evolboot.identity.domain.role;

import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author evol
 */
@Service
public class RoleBatchDeleteService {

    private final RoleRepository repository;

    public RoleBatchDeleteService(RoleRepository repository) {
        this.repository = repository;
    }

    public void batchDelete(List<Long> ids) {
        ids.forEach(repository::deleteById);
    }
}
