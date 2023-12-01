package org.evolboot.identity.domain.userrole.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.entity.AbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 用户角色
 *
 * @author evol
 */
@Table(name = "evoltb_identity_user_role")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@Schema(title = "用户角色")
public class UserRole extends AbstractEntity<Long> implements AggregateRoot<UserRole> {

    @Id
    private Long id;

    private Long userId;

    private Long roleId;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public UserRole(Long userId, Long roleId) {
        generateId();
        this.userId = userId;
        this.roleId = roleId;
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public UserRole root() {
        return this;
    }
}
