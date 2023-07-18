package org.evolboot.identity.domain.role.service;

import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author evol
 */
@Service
public class RoleDeletePermissionService {

    private final RoleRepository repository;

    public RoleDeletePermissionService(RoleRepository repository) {
        this.repository = repository;
    }

    /**
     * 权限和角色数据不多,且操作也不多,为了方便,就全量查询和保存
     *
     * @param permissionIds
     */
    public void execute(List<Long> permissionIds) {
        List<Role> roles = repository.findAll();
        for (Role role : roles) {
            for (Long permissionId : permissionIds) {
                role.deletePermission(permissionId);
            }
        }
        repository.saveAll(roles);
    }
}
