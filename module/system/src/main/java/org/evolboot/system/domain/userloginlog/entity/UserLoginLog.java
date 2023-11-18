package org.evolboot.system.domain.userloginlog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author evol
 */
@Table(name = "evoltb_system_user_login_log")
@Entity
@Slf4j
@Getter
@NoArgsConstructor
@ToString
@Schema(description = "用户登录日志")
public class UserLoginLog extends JpaAbstractEntity<Long> implements AggregateRoot<UserLoginLog> {

    @Id
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @JsonIgnore
    private String login_token;

    @Schema(description = "登录IP")
    private String loginIp;

    @Schema(description = "位置")
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
