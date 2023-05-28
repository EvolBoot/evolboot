package org.evolboot.identity.domain.role.entity;

import com.google.common.collect.Sets;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.i18n.I18NMessageAssert;
import org.evolboot.core.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
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

    @Builder
    public Role(String roleName, Set<Long> permissions, String remark) {
        generateId();
        setRoleName(roleName);
        setRemark(remark);
        setPermissions(permissions);
    }

    public void update(String roleName, Set<Long> permissions, String remark) {
        setRoleName(roleName);
        updatePermissions(permissions);
        setRemark(remark);
    }
}
