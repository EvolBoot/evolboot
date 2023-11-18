package org.evolboot.identity.domain.userid.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.entity.AbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;

import jakarta.persistence.*;

/**
 * UserId
 *
 * @author evol
 */
@Table(name = "evoltb_identity_user_id")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class UserId extends AbstractEntity<Long> implements AggregateRoot<UserId> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean state = false;

    public void use() {
        // 该用户ID已使用
        Assert.isTrue(!state, IdentityI18nMessage.UserId.hasBeenUsed());
        this.state = true;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public UserId root() {
        return this;
    }
}
