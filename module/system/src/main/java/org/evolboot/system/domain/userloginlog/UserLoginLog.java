package org.evolboot.system.domain.userloginlog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author evol
 */
@Table(name = "evoltb_system_user_login_log")
@Entity
@Slf4j
@Getter
@NoArgsConstructor
@ToString
public class UserLoginLog extends JpaAbstractEntity<Long> implements AggregateRoot<UserLoginLog> {

    @Id
    private Long id;

    private Long userId;

    @JsonIgnore
    private String login_token;

    private String loginIp;

    private String physicalLocation;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public UserLoginLog(Long userId, String login_token, String loginIp, String physicalLocation) {
        this.generateId();
        this.userId = userId;
        this.login_token = login_token;
        this.loginIp = loginIp;
        this.physicalLocation = physicalLocation;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public UserLoginLog root() {
        return this;
    }
}
