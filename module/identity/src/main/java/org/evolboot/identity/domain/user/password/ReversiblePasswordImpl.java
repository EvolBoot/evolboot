package org.evolboot.identity.domain.user.password;

/**
 * @author evol
 */
public class ReversiblePasswordImpl implements ReversiblePassword {

    private String originalPassword;

    public ReversiblePasswordImpl(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    @Override
    public String toOriginalPassword() {
        return originalPassword;
    }
}
