package org.evolboot.identity.domain.role.entity;

import com.google.common.collect.Sets;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.domain.permission.entity.PermissionScope;
import org.springframework.util.CollectionUtils;

import jakarta.persistence.*;
import java.util.Set;

/**
 * @author evol
 */
@Entity
@Table(name = "evoltb_identity_role")
@Getter
@Slf4j
@NoArgsConstructor
//TODO 多语言
public class Role extends JpaAbstractEntity<Long> implements AggregateRoot<Role> {

    @Id
    private Long id;

    private String roleName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "evoltb_identity_role_permission",
            joinColumns = @JoinColumn(name = "role_id_", referencedColumnName = "id_"))
    @Column(name = "permission_id_")
    private Set<Long> permissions = Sets.newHashSet();


    private String remark;

    @Enumerated(EnumType.STRING)
    private PermissionScope scope = PermissionScope.PLATFORM;

    private Long tenantId;

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Role root() {
        return this;
    }

    public void deletePermission(Long permissionId) {
        this.permissions.remove(permissionId);
    }

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    private void setRoleName(String roleName) {
        Assert.notBlank(roleName, "角色名称不能为空");
        this.roleName = roleName;
    }

    private void setPermissions(Set<Long> permissions) {
        this.permissions.clear();
        if (!CollectionUtils.isEmpty(permissions)) {
            this.permissions.addAll(permissions);
        }
    }


    /**
     * 更新权限
     *
     * @param permissions
     */
    private void updatePermissions(Set<Long> permissions) {
        setPermissions(permissions);
    }

    private void setRemark(String remark) {
        this.remark = remark;
    }

    private void setScope(PermissionScope scope) {
        if (scope == null) {
            scope = PermissionScope.PLATFORM;
        }
        this.scope = scope;
    }

    private void setTenantId(Long tenantId) {
        // 租户角色必须有 tenantId
        if (scope == PermissionScope.TENANT) {
            Assert.notNull(tenantId, "租户角色必须指定租户ID");
        }
        this.tenantId = tenantId;
    }

    @Builder
    public Role(String roleName, Set<Long> permissions, String remark, PermissionScope scope, Long tenantId) {
        generateId();
        setRoleName(roleName);
        setRemark(remark);
        setPermissions(permissions);
        setScope(scope);
        setTenantId(tenantId);
    }

    public void update(String roleName, Set<Long> permissions, String remark) {
        setRoleName(roleName);
        updatePermissions(permissions);
        setRemark(remark);
        // scope 和 tenantId 创建后不允许修改
    }
}
