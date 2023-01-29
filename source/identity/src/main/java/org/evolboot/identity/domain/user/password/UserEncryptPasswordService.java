package org.evolboot.identity.domain.user.password;

/**
 * @author evol

 */
public interface UserEncryptPasswordService {

    ReversiblePassword toReversiblePassword(String encodePassword);

}
