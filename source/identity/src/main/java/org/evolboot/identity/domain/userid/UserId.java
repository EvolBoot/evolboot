package org.evolboot.identity.domain.userid;

import org.evolboot.core.domain.AbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * UserId
 *
 * @author evol
 * 
 */
@Table(name = "evol_identity_user_id")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class UserId extends AbstractEntity<Long> implements AggregateRoot<UserId> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean status = false;

    void use() {
        // 该用户ID已使用
        Assert.isTrue(!status, IdentityI18nMessage.UserId.hasBeenUsed());
        this.status = true;
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
