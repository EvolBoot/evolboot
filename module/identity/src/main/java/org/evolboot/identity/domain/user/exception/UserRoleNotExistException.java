package org.evolboot.identity.domain.user.exception;

import org.evolboot.core.exception.DomainException;

/**
 * @author evol
 */
public class UserRoleNotExistException extends DomainException {
    public UserRoleNotExistException(String message) {
        super(message);
    }
}
