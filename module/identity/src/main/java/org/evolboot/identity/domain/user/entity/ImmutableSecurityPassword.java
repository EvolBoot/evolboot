package org.evolboot.identity.domain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.evolboot.identity.domain.user.util.UserPasswordUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * @author evol
 */
@NoArgsConstructor
@Embeddable
@Getter
public class ImmutableSecurityPassword {

    @Column(name = "security_password_")
    private String password;

    public ImmutableSecurityPassword(String password) {
        this.password = UserPasswordUtil.encode(password);
    }

    public static ImmutableSecurityPassword of(String originalPassword) {
        return new ImmutableSecurityPassword(originalPassword);
    }

    public boolean matches(String originalPassword) {
        return UserPasswordUtil.matches(originalPassword, password);
    }
}
