package org.evolboot.security.accesstoken.exception;

import org.evolboot.core.exception.DomainException;

/**
 * @author evol
 */

public class AccessTokenException extends DomainException {
    public AccessTokenException(String message) {
        super(message);
    }
}
