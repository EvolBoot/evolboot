package org.evolboot.identity.domain.user.entity;

import lombok.NoArgsConstructor;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.util.UserPasswordUtil;
import org.evolboot.identity.domain.user.util.UserValidUtil;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author evol
 */
@NoArgsConstructor
@Embeddable
public class ImmutablePassword {

    @Column(name = "password_")
    private String password;

    public ImmutablePassword(String password) {
        Assert.notBlank(password, IdentityI18nMessage.User.passwordNotEmpty());
        Assert.isTrue(UserValidUtil.checkPassword(password), IdentityI18nMessage.User.passwordValidFail());
        this.password = UserPasswordUtil.encode(password);
    }

    public static ImmutablePassword of(String originalPassword) {
        return new ImmutablePassword(originalPassword);
    }

    public boolean matches(String originalPassword) {
        return UserPasswordUtil.matches(originalPassword, password);
    }
}
