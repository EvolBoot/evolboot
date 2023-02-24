package org.evolboot.identity.domain.userrole;

import org.evolboot.core.domain.AbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Set;

/**
 * 用户角色
 *
 * @author evol
 * 
 */
@Table(name = "evoltb_identity_user_role")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
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
