package org.evolboot.identity.domain.role;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.service.RoleCreateFactory;
import org.evolboot.identity.domain.role.dto.RoleQueryRequest;
import org.evolboot.identity.domain.role.service.RoleUpdateService;

import java.util.Collection;
import java.util.List;

/**
 * @author evol
 */
public interface RoleAppService {

    Role create(RoleCreateFactory.Request request);

    void update(RoleUpdateService.Request request);

    void delete(Long id);

    void deleteBatch(List<Long> ids);

    Page<Role> page(RoleQueryRequest query);

    List<Role> findAllByIdAndTenantId(Collection<Long> roleIds, Long tenantId);

    Role findById(Long id);

    /**
     * 查找租户角色并验证权限
     *
     * @param id       角色ID
     * @param tenantId 租户ID
     * @return 角色信息
     * @throws org.evolboot.core.exception.DomainNotFoundException 如果角色不存在或不属于该租户
     */
    Role findTenantRoleById(Long id, Long tenantId);

    /**
     * 更新租户角色（带租户验证）
     *
     * @param request  更新请求
     * @param tenantId 租户ID
     * @throws org.evolboot.core.exception.DomainNotFoundException 如果角色不存在或不属于该租户
     */
    void updateTenantRole(RoleUpdateService.Request request, Long tenantId);

    /**
     * 删除租户角色（带租户验证）
     *
     * @param id       角色ID
     * @param tenantId 租户ID
     * @throws org.evolboot.core.exception.DomainNotFoundException 如果角色不存在或不属于该租户
     */
    void deleteTenantRole(Long id, Long tenantId);

}
